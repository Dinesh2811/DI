plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.kotlinAndroid)
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")   // ksp
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.dinesh.android"
    compileSdk = 34
    compileSdkPreview = "UpsideDownCake"

    defaultConfig {
        applicationId = "com.dinesh.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            testCoverage {
                enableUnitTestCoverage = false
                enableAndroidTestCoverage = false
            }
        }
        debug {
//            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            isShrinkResources = false
            testCoverage {
                enableUnitTestCoverage = true
                enableAndroidTestCoverage = true
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        getByName("debug") {
//            storeFile = file("../keystore/debug.keystore")
//            storePassword = "android_storePassword"
//            keyAlias = "android_debug_key"
//            keyPassword = "android_keyPassword"
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":dagger")))
    implementation(project(mapOf("path" to ":hilt")))
    //  Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")
    ksp("androidx.hilt:hilt-compiler:1.0.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.11.0-alpha03")

    implementation("androidx.compose.material3:material3:1.2.0-alpha09")
    implementation("androidx.compose.material3:material3-android:1.2.0-alpha09")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0-alpha09")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation(platform("androidx.compose:compose-bom:2023.10.00"))
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.ui:ui-graphics:1.5.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.compose.foundation:foundation:1.5.3")
    implementation("androidx.compose.ui:ui-geometry:1.5.3")
    implementation("androidx.compose.foundation:foundation-layout:1.5.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.3")
    implementation("androidx.compose.animation:animation-core:1.5.3")
    implementation("androidx.compose.animation:animation:1.5.3")
    implementation("androidx.compose.ui:ui-text:1.5.3")
    implementation("androidx.compose.ui:ui-util:1.5.3")
    implementation("androidx.compose.ui:ui-viewbinding:1.5.3")
    implementation("androidx.compose.material:material:1.5.3")
    implementation("androidx.compose.material:material-icons-core:1.5.3")
    implementation("androidx.compose.material:material-icons-extended:1.5.3")

    // androidTestImplementation
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // testImplementation
    testImplementation("junit:junit:4.13.2")

    // debugImplementation
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.3")


    //  Room components    	2.5.2   -->  2.6.0-alpha03
    implementation("androidx.room:room-runtime:2.6.0-rc01")
    ksp("androidx.room:room-compiler:2.6.0-rc01")
    implementation("androidx.room:room-ktx:2.6.0-rc01")

    //  ViewModel & LiveData
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:retrofit-mock:2.9.0")

    // Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson Converter
    implementation("com.google.code.gson:gson:2.10.1") // Used to convert Java Object into JSON representation

    // HTTP
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.8")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.8")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    implementation("androidx.navigation:navigation-compose:2.7.4")  // Navigation Compose

    // Paging
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")

    // Animation
    implementation("com.airbnb.android:lottie:6.1.0")   // Lottie
    implementation("com.facebook.shimmer:shimmer:0.5.0")    //  Shimmer

    // Location Services
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // RecyclerView
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Volley
    implementation("com.android.volley:volley:1.2.1")

}

/*

dependencies {
    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    //  ViewModel & LiveData
    implementation(libs.bundles.lifecycle)

    //  Room components
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)    //  kapt or ksp

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Gson
    implementation(libs.bundles.gson)

    // HTTP
    implementation(libs.bundles.okhttp)

    // Navigation Component
    implementation(libs.bundles.navigation)

    // Paging
    implementation(libs.bundles.paging)

    // Animation
    implementation(libs.bundles.animation)

    // Location Services
    implementation(libs.play.services.location)

    // RecyclerView
    implementation(libs.androidx.swiperefreshlayout)

    // Volley
    implementation(libs.volley)

    androidTestImplementation(libs.bundles.android.test)
    debugImplementation(libs.bundles.debug)
    testImplementation(libs.bundles.test)
}

 */

/*
libs.versions.toml


[versions]
agp = "8.1.1"
kotlin = "1.9.10"

core-ktx = "1.12.0"
activity-compose = "1.7.2"
appcompat = "1.6.1"
constraintlayout = "2.1.4"
material = "1.11.0-alpha02"

material3 = "1.2.0-alpha07"
kotlinx-coroutines = "1.7.3"

compose-bom = "2023.09.00"
ui = "1.5.1"
compose-material = "1.5.1"

ui-test-junit4 = "1.5.1"
test-junit = "1.1.5"
espresso-core = "3.5.1"
junit = "4.13.2"

room = "2.6.0-beta01"  #   2.5.2   -->   2.6.0-alpha03
lifecycle = "2.6.2"
glide = "4.16.0"
retrofit = "2.9.0"
okhttp = "5.0.0-alpha.8"

converter-gson = "2.9.0"
gson = "2.10.1"

navigation = "2.7.2"
lottie = "6.1.0"
volley = "1.2.1"
swiperefreshlayout = "1.1.0"

lifecycle-extensions = "2.2.0"
paging-runtime-ktx = "3.2.1"

play-services-location = "21.0.1"
shimmer = "0.5.0"


[libraries]
#   implementation
androidx-core-ktx = { module = "androidx.core:core-ktx", version.ref = "core-ktx" }
androidx-activity-compose = { module = "androidx.activity:activity-compose", version.ref = "activity-compose" }
androidx-appcompat = { module = "androidx.appcompat:appcompat", version.ref = "appcompat" }
androidx-constraintlayout = { module = "androidx.constraintlayout:constraintlayout", version.ref = "constraintlayout" }
material = { module = "com.google.android.material:material", version.ref = "material" }

androidx-material3 = { module = "androidx.compose.material3:material3", version.ref = "material3" }
androidx-material3-android = { module = "androidx.compose.material3:material3-android", version.ref = "material3" }
androidx-material3-window-size = { module = "androidx.compose.material3:material3-window-size-class", version.ref = "material3" }

kotlinx-coroutines = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-android", version.ref = "kotlinx-coroutines" }

androidx-compose-bom = { module = "androidx.compose:compose-bom", version.ref = "compose-bom" }
androidx-ui = { module = "androidx.compose.ui:ui", version.ref = "ui" }
androidx-ui-graphics = { module = "androidx.compose.ui:ui-graphics", version.ref = "ui" }
androidx-ui-tooling-preview = { module = "androidx.compose.ui:ui-tooling-preview", version.ref = "ui" }
androidx-foundation = { module = "androidx.compose.foundation:foundation", version.ref = "ui" }
androidx-ui-geometry = { module = "androidx.compose.ui:ui-geometry", version.ref = "ui" }
androidx-foundation-layout = { module = "androidx.compose.foundation:foundation-layout", version.ref = "ui" }
androidx-runtime-livedata = { module = "androidx.compose.runtime:runtime-livedata", version.ref = "ui" }
androidx-animation-core = { module = "androidx.compose.animation:animation-core", version.ref = "ui" }
androidx-animation = { module = "androidx.compose.animation:animation", version.ref = "ui" }
androidx-ui-text = { module = "androidx.compose.ui:ui-text", version.ref = "ui" }
androidx-ui-util = { module = "androidx.compose.ui:ui-util", version.ref = "ui" }
androidx-ui-viewbinding = { module = "androidx.compose.ui:ui-viewbinding", version.ref = "ui" }
androidx-material = { module = "androidx.compose.material:material", version.ref = "compose-material" }
androidx-material-icons-core = { module = "androidx.compose.material:material-icons-core", version.ref = "compose-material" }
androidx-material-icons-extended = { module = "androidx.compose.material:material-icons-extended", version.ref = "compose-material" }

#  androidTestImplementation
androidx-ui-test-junit4 = { module = "androidx.compose.ui:ui-test-junit4", version.ref = "ui-test-junit4" }
androidx-espresso-core = { module = "androidx.test.espresso:espresso-core", version.ref = "espresso-core" }
androidx-junit = { module = "androidx.test.ext:junit", version.ref = "test-junit" }

#  testImplementation
junit = { module = "junit:junit", version.ref = "junit" }

#  debugImplementation
androidx-ui-tooling = { module = "androidx.compose.ui:ui-tooling", version.ref = "ui-test-junit4" }
androidx-ui-test-manifest = { module = "androidx.compose.ui:ui-test-manifest", version.ref = "ui-test-junit4" }

#  ViewModel & LiveData
androidx-lifecycle-runtime-ktx = { module = "androidx.lifecycle:lifecycle-runtime-ktx", version.ref = "lifecycle" }
androidx-lifecycle-viewmodel-ktx = { module = "androidx.lifecycle:lifecycle-viewmodel-ktx", version.ref = "lifecycle" }
androidx-lifecycle-viewmodel-compose = { module = "androidx.lifecycle:lifecycle-viewmodel-compose", version.ref = "lifecycle" }
androidx-lifecycle-livedata-ktx = { module = "androidx.lifecycle:lifecycle-livedata-ktx", version.ref = "lifecycle" }
androidx-lifecycle-viewmodel-savedstate = { module = "androidx.lifecycle:lifecycle-viewmodel-savedstate", version.ref = "lifecycle" }
androidx-lifecycle-runtime-compose = { module = "androidx.lifecycle:lifecycle-runtime-compose", version.ref = "lifecycle" }

#  Room components
androidx-room-runtime = { module = "androidx.room:room-runtime", version.ref = "room" }
androidx-room-compiler = { module = "androidx.room:room-compiler", version.ref = "room" }
androidx-room-ktx = { module = "androidx.room:room-ktx", version.ref = "room" }

#  Glide
glide = { module = "com.github.bumptech.glide:glide", version.ref = "glide" }
compiler = { module = "com.github.bumptech.glide:compiler", version.ref = "glide" }

#  Retrofit
retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
retrofit-mock = { module = "com.squareup.retrofit2:retrofit-mock", version.ref = "retrofit" }

#  Gson
converter-gson = { module = "com.squareup.retrofit2:converter-gson", version.ref = "converter-gson" }
gson = { module = "com.google.code.gson:gson", version.ref = "gson" }

#  HTTP
okhttp = { module = "com.squareup.okhttp3:okhttp", version.ref = "okhttp" }
logging-interceptor = { module = "com.squareup.okhttp3:logging-interceptor", version.ref = "okhttp" }

#  Navigation Component
androidx-navigation-fragment-ktx = { module = "androidx.navigation:navigation-fragment-ktx", version.ref = "navigation" }
androidx-navigation-ui-ktx = { module = "androidx.navigation:navigation-ui-ktx", version.ref = "navigation" }
androidx-navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigation" }

#  Paging
androidx-lifecycle-extensions = { module = "androidx.lifecycle:lifecycle-extensions", version.ref = "lifecycle-extensions" }
androidx-paging-runtime-ktx = { module = "androidx.paging:paging-runtime-ktx", version.ref = "paging-runtime-ktx" }

#  Animation
lottie = { module = "com.airbnb.android:lottie", version.ref = "lottie" }   # Lottie
shimmer = { module = "com.facebook.shimmer:shimmer", version.ref = "shimmer" }  # Shimmer

#  Location Services
play-services-location = { module = "com.google.android.gms:play-services-location", version.ref = "play-services-location" }

#  RecyclerView
androidx-swiperefreshlayout = { module = "androidx.swiperefreshlayout:swiperefreshlayout", version.ref = "swiperefreshlayout" }

#  Volley
volley = { module = "com.android.volley:volley", version.ref = "volley" }



[plugins]
androidApplication = { id = "com.android.application", version.ref = "agp" }
kotlinAndroid = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }



[bundles]
android = ["androidx-core-ktx", "androidx-activity-compose", "androidx-appcompat", "androidx-constraintlayout", "material",
    "androidx-material3", "androidx-material3-android", "androidx-material3-window-size", "kotlinx-coroutines"]

compose = ["androidx-ui", "androidx-ui-graphics", "androidx-ui-tooling-preview", "androidx-foundation",
    "androidx-ui-geometry", "androidx-foundation-layout", "androidx-runtime-livedata", "androidx-animation-core",
    "androidx-animation", "androidx-ui-text", "androidx-ui-util", "androidx-ui-viewbinding", "androidx-material",
    "androidx-material-icons-core", "androidx-material-icons-extended"]

lifecycle = ["androidx-lifecycle-runtime-ktx", "androidx-lifecycle-viewmodel-ktx", "androidx-lifecycle-viewmodel-compose",
    "androidx-lifecycle-livedata-ktx", "androidx-lifecycle-viewmodel-savedstate", "androidx-lifecycle-runtime-compose"]

room = ["androidx-room-runtime", "androidx-room-ktx"]
retrofit = ["retrofit", "retrofit-mock"]
gson = ["converter-gson", "gson"]
okhttp = ["okhttp", "logging-interceptor"]
navigation = ["androidx-navigation-fragment-ktx", "androidx-navigation-ui-ktx", "androidx-navigation-compose"]
paging = ["androidx-lifecycle-extensions", "androidx-paging-runtime-ktx"]
animation = ["lottie", "shimmer"]

android-test = ["androidx-ui-test-junit4", "androidx-espresso-core", "androidx-junit"]
debug = ["androidx-ui-tooling", "androidx-ui-test-manifest"]
test = ["junit"]

 */