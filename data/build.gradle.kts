plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.petapp.data"
    compileSdk = ConfigurationData.compileSdk
    buildToolsVersion = ConfigurationData.buildToolsVersion

    defaultConfig {
        minSdk = ConfigurationData.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    externalNativeBuild {
        cmake {
            version = ConfigurationData.cMakeVersion
            path(file("src/CMakeLists.txt"))
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Libs.CoreCtx.coreKtx)
    implementation(Libs.Hilt.hilt)
    kapt(Libs.Hilt.hiltCompiler)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.retrofitRx3)
    implementation(Libs.Retrofit.retrofitGson)
    implementation(Libs.OkHttp.okHttp)
    implementation(Libs.OkHttp.okHttpLogging)
    implementation(Libs.Security.crypto)

    testImplementation(Libs.JUnit.junit)
    androidTestImplementation(Libs.JUnitExt.junitExt)
    androidTestImplementation(Libs.Espresso.espresso)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}