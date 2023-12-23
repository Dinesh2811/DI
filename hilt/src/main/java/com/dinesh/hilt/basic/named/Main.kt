package com.dinesh.hilt.basic.named

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("Retrofit1")
    fun providesRetrofit1() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://sandbox.plaid.com")
            .build()
    }


    @Singleton
    @Provides
    @Named("Retrofit2")
    fun providesRetrofit2() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://google.com")
            .build()
    }

    @Singleton
    @Provides
    @Named("ApiService1")
    fun providesApiService1(@Named("Retrofit1") retrofit : Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java) // Use Base_URL_1 to create retrofit instance
    }

    @Singleton
    @Provides
    @Named("ApiService2")
    fun providesApiService2(@Named("Retrofit2") retrofit : Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java) // Use Base_URL_2 to create retrofit instance
    }

}

class Repository @Inject constructor(@Named("ApiService1") private val apiService : ApiService) {

}

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

}

interface ApiService {
    @GET("todos")
    suspend fun fetchTodos(): Response<List<Todo>>
}

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
