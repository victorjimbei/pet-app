object Libs {

    object CoreCtx {
        private const val version = "1.12.0"
        const val coreKtx = "androidx.core:core-ktx:$version"
    }

    object AndroidX {
        object AppCompat {
            private const val version = "1.6.1"
            const val appcompat = "androidx.appcompat:appcompat:$version"
        }

        object ConstraintLayout {
            private const val version = "2.1.4"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
        }
    }

    object Material {
        private const val version = "1.11.0"
        const val meterial = "com.google.android.material:material:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:$version"
        const val retrofitRx3 = "com.squareup.retrofit2:adapter-rxjava3:$version"
    }

    object OkHttp {
        private const val version = "4.12.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:$version"
        const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Glide {
        private const val version = "4.12.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
    }

    object Room {
        private const val version = "2.6.1"
        const val room = "androidx.room:room-runtime:$version"
        const val roomCompiler = "androidx.room:room-compiler:$version"
        const val roomRx = "androidx.room:room-rxjava3:$version"
    }

    object Navigation {
        private const val version = "2.7.6"
        const val navigationFragment = "androidx.navigation:navigation-fragment:$version"
    }

    object LiveData {
        private const val version = "2.7.0"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
    }

    object Hilt {
        const val version = "2.44"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
    }

    // Unit tests dependencies
    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
    }

    object JUnitExt {
        private const val version = "1.1.5"
        const val junitExt = "androidx.test.ext:junit:$version"
    }

    object Espresso {
        private const val version = "3.5.1"
        const val espresso = "androidx.test.espresso:espresso-core:$version"
    }
}