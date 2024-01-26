import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.petapp.data"
    compileSdk = ConfigurationData.compileSdk
    buildToolsVersion = ConfigurationData.buildToolsVersion

    defaultConfig {
        minSdk = ConfigurationData.minSdk

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.incremental", "true")
            arg("room.expandProjection", "true")
        }

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

    tasks.withType<Test> {
        useJUnitPlatform()

        testLogging {
            events(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Libs.CoreCtx.coreKtx)
    implementation(Libs.Hilt.hilt)
    ksp(Libs.Hilt.hiltCompiler)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.retrofitRx3)
    implementation(Libs.Retrofit.retrofitGson)
    implementation(Libs.OkHttp.okHttp)
    implementation(Libs.OkHttp.okHttpLogging)
    implementation(Libs.AndroidX.Security.crypto)
    implementation(Libs.AndroidX.Room.room)
    implementation(Libs.AndroidX.Room.roomRx)
    ksp(Libs.AndroidX.Room.roomCompiler)
    implementation(Libs.RX.rxAndroid)
    testImplementation(Libs.Junit.junitApi)
    testImplementation(Libs.Junit.junitEngine)
    testImplementation(Libs.Junit.junitParams)
    testImplementation(Libs.Mockk.mockk)
    androidTestImplementation(Libs.JUnitExt.junitExt)
    androidTestImplementation(Libs.Espresso.espresso)
}