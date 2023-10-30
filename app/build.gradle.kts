@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.com.google.dagger.hilt)
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "kz.kcell.kcellbootcamp"
    compileSdk = 34

    defaultConfig {
        applicationId = "kz.kcell.kcellbootcamp"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_KEY", "\"fa84dfb57a1472eb1a865ef9709b5447\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "IMAGE_URL", "\"https://image.tmdb.org/t/p/w500/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }


}

kapt {
    correctErrorTypes = true
}

dependencies {

    // Core
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // ViewModel + LiveData
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    // Timber
    implementation(libs.timber)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)

    // Navigation
    implementation(libs.navigationFragments)
    implementation(libs.navigationUi)

    // Glide
    implementation(libs.glide)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hiltKapt)

    // Room
    implementation(libs.room)
    annotationProcessor(libs.roomCompiler)
    ksp(libs.roomCompiler)
    implementation(libs.roomCoroutines)

    // ViewBinding
    implementation(libs.viewBinding)

    // Chucker
    debugImplementation(libs.chuckerDebug)
    releaseImplementation(libs.chuckerRelease)
}
