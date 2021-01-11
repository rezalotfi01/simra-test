
object ApplicationId {
    const val id = "simra.androidtest.rezalotfi"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Modules {
    const val navigation = ":navigation"
    const val model = ":data:model"
    const val local = ":data:local"
    const val remote = ":data:remote"
    const val repository = ":data:repository"
    const val home = ":features:home"
    const val moviedetail = ":features:moviedetail"
    const val common = ":common"
    const val commonTest = ":common_test"
}

object Versions {
    const val gradle = "4.1.0"
    const val safeArgs = "2.3.2"

    const val compileSdk = 30
    const val minSdk = 16
    const val targetSdk = 30

    const val multidex = "2.0.1"
    const val kotlin = "1.4.21"
    const val coroutines = "1.4.0"

    const val coreKtx = "1.0.1"
    const val constraintLayout = "1.1.3"
    const val recyclerview = "1.0.0"
    const val cardView = "1.0.0"
    const val coil = "0.6.1"

    const val koin = "2.0.1"

    const val appCompat = "1.0.2"
    const val nav = "2.3.2"

    const val room = "2.1.0"

    const val gson = "2.8.6"
    const val okHttp = "4.9.0"
    const val retrofit = "2.9.0"

    const val lifecycle = "2.2.0"

    const val androidTestRunner = "1.2.0"
    const val espressoCore = "3.2.0"

    const val archCoreTest = "2.0.0"
    const val androidJunit = "1.1.0"
    const val mockk = "1.9.2"
    const val fragmentTest = "1.1.0"
    const val databinding = "3.3.2"
}

object Libraries {
    // KOIN
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinCore = "org.koin:koin-core:${Versions.koin}\""
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    // ROOM
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    // RETROFIT
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    // COIL
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    // MULTIDEX
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
}

object AndroidLibraries {
    // KOTLIN
    const val kotlinCoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    // ANDROID
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
}

object KotlinLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object TestLibraries {
    // Test Deps
    const val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
    const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCoreTest}"
    const val junit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val fragmentNav = "androidx.fragment:fragment-testing:${Versions.fragmentTest}"
    // KOIN
    const val koin = "org.koin:koin-test:${Versions.koin}"
    // MOCK WEBSERVER
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    // MOCK
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    // COROUTINE
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    // DATA BINDING
    const val databinding = "androidx.databinding:databinding-compiler:${Versions.databinding}"
}