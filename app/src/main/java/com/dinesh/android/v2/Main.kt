package com.dinesh.android.v2

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val TAG = "log_API_v3"

class Main : AppCompatActivity() {
    private val apiService = ApiClient.getApiInterface<ApiService>(Constants.BASE_URL)
    private val apiRepositoryImpl: ApiRepositoryImpl = ApiRepositoryImpl(apiService)
    private val apiViewModel: ApiViewModel by viewModels {
        ApiViewModelFactory(apiRepositoryImpl, 4)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.Main) {
            apiViewModel.fetchTodos()
        }
        apiViewModel.todosLiveData.observe(this) { state ->
            handleApiState(state)
        }
    }
    private fun handleApiState(apiState: ApiState<List<Todo>>) {
        when (apiState) {
            is ApiState.Loading -> handleLoadingState()
            is ApiState.Success -> {
                val todos: List<Todo> = apiState.data
                updateUI(todos)
            }
            is ApiState.Error -> {
                val errorMessage: String = apiState.message
                val data: List<Todo>? = apiState.data
                showError(errorMessage)
                updateUI(data)
            }
            is ApiState.Exception -> {
                val exception: Throwable = apiState.exception
                handleException(exception)
            }
        }
    }

    private fun handleLoadingState() {
        Log.i(TAG, "handleLoadingState")
    }

    private fun updateUI(todos: List<Todo>?) {
        Log.d(TAG, "updateUI: ${todos?.get(0)}")
    }

    private fun showError(errorMessage: String) {
        Log.w(TAG, "showError: $errorMessage" )
    }

    private fun handleException(exception: Throwable) {
        Log.e(TAG, "handleException: ${exception.message}")
    }

}

class ApiViewModel(private val repository: ApiRepository, private val position: Int) : ViewModel(), HandleApiState {
    private val _todosLiveData = MutableLiveData<ApiState<List<Todo>>>()
    val todosLiveData: LiveData<ApiState<List<Todo>>> = _todosLiveData

    suspend fun fetchTodos(): List<Todo>? {
        _todosLiveData.value = ApiState.Loading
        handleLoadingState()
        return try {
            when (val apiState = repository.getTodos()) {
                is ApiState.Success -> {
                    val todos: List<Todo> = apiState.data
                    _todosLiveData.value = ApiState.Success(todos)
                    updateUI(todos, position)
                }
                is ApiState.Error -> {
                    val errorMessage: String = apiState.message
                    val data: List<Todo>? = apiState.data
                    _todosLiveData.value = ApiState.Error(errorMessage, data)
                    showError(errorMessage)
                    updateUI(data)
                }
                is ApiState.Exception -> {
                    val exception: Throwable = apiState.exception
                    _todosLiveData.value = ApiState.Exception(exception, null)
                    handleException(exception)
                    updateUI(null)
                }
                else -> updateUI(null)
            }
        } catch (e: Exception) {
            _todosLiveData.value = ApiState.Exception(e, null)
            handleException(e)
            updateUI(null)
        }
    }

}

interface HandleApiState {
    suspend fun handleLoadingState() {
        Log.i(TAG, "handleLoadingState")
    }

    suspend fun updateUI(todos: List<Todo>?, position: Int = 0): List<Todo>? {
        Log.d(TAG, "updateUI: ${todos?.get(position)}")
        return todos
    }

    suspend fun showError(errorMessage: String) {
        Log.w(TAG, "showError: $errorMessage" )
    }

    suspend fun handleException(exception: Throwable) {
        Log.e(TAG, "handleException: ${exception.message}")
    }
}

interface ApiRepository {
    suspend fun getTodos(): ApiState<List<Todo>>
}

class ApiRepositoryImpl(private val apiService: ApiService): ApiRepository {
    override suspend fun getTodos(): ApiState<List<Todo>> {
        return try {
            val response = apiService.getTodos()
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiState.Success(it)
                } ?: ApiState.Error("No data available.", null)
            } else {
                ApiState.Error("Failed to fetch data", null)
            }
        } catch (e: Exception) {
            ApiState.Exception(e, null)
        }
    }
}

object ApiClient {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getApiInterface(apiInterface: Class<T>, baseUrl: String = "http://10.0.2.2/"): T {
        return createRetrofit(baseUrl).create(apiInterface)
    }

    inline fun <reified T> getApiInterface(baseUrl: String = "http://10.0.2.2/"): T {
        return getApiInterface(T::class.java, baseUrl)
    }
}

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): Response<List<Todo>>
}

sealed class ApiState<out T> {
    data object Loading : ApiState<Nothing>()
    data class Success<T>(val data: T) : ApiState<T>()
    data class Error<T>(val message: String, val data: T?) : ApiState<T>()
    data class Exception<T>(val exception: Throwable, val data: T?) : ApiState<T>()
}

class ApiViewModelFactory(private val repository: ApiRepository, private val position: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApiViewModel(repository, position) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

object Constants {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"
}

