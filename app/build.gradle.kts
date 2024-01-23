plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.petapp"
    compileSdk = ConfigurationData.compileSdk
    buildToolsVersion = ConfigurationData.buildToolsVersion

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
    implementation(Libs.CoreCtx.coreKtx)
    implementation(Libs.AndroidX.AppCompat.appcompat)
    implementation(Libs.Material.meterial)
    implementation(Libs.AndroidX.ConstraintLayout.constraintLayout)
    implementation(Libs.Hilt.hilt)
    implementation(Libs.Navigation.navigationFragment)
    kapt(Libs.Hilt.hiltCompiler)
    testImplementation(Libs.JUnit.junit)
    androidTestImplementation(Libs.JUnitExt.junitExt)
    androidTestImplementation(Libs.Espresso.espresso)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}