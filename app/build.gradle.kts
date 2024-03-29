plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.petapp"
    compileSdk = ConfigurationData.compileSdk
    buildToolsVersion = ConfigurationData.buildToolsVersion

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = ConfigurationData.applicationId
        minSdk = ConfigurationData.minSdk
        targetSdk = ConfigurationData.targetSdk
        versionCode = ConfigurationData.versionCode
        versionName = ConfigurationData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(project(":data"))
    implementation(Libs.CoreCtx.coreKtx)
    implementation(Libs.AndroidX.AppCompat.appcompat)
    implementation(Libs.Material.meterial)
    implementation(Libs.AndroidX.ConstraintLayout.constraintLayout)
    implementation(Libs.Hilt.hilt)
    kapt(Libs.Hilt.hiltCompiler)
    implementation(Libs.AndroidX.ViewBinding.viewBinding)
    implementation(Libs.AndroidX.Navigation.navigationFragment)
    implementation(Libs.RX.rxAndroid)
    implementation(Libs.RX.rxKotlin)
    implementation(Libs.Glide.glide)
    implementation(Libs.Glide.glideOkHttp)
    implementation(Libs.OkHttp.okHttp)
    implementation(Libs.AndroidX.SwipeRefreshLayout.swipeRefreshLayout)
    implementation(Libs.AndroidX.SavedState.savedState)
    implementation(Libs.AndroidX.SplashScreen.splashScreen)

    androidTestImplementation(Libs.JUnitExt.junitExt)
    androidTestImplementation(Libs.Espresso.espresso)

    debugImplementation(Libs.Flipper.flipper)
    debugImplementation(Libs.SoLoader.soLoader)
}