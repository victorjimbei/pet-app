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

        object ViewBinding {
            private const val version = "8.2.1"
            const val viewBinding = "androidx.databinding:viewbinding:$version"
        }

        object SwipeRefreshLayout {
            private const val version = "1.1.0"
            const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:$version"
        }

        object Room {
            private const val version = "2.6.1"
            const val room = "androidx.room:room-runtime:$version"
            const val roomCompiler = "androidx.room:room-compiler:$version"
            const val roomRx = "androidx.room:room-rxjava3:$version"
            const val roomPaging = "androidx.room:room-paging:$version"
        }

        object Navigation {
            private const val version = "2.7.6"
            const val navigationFragment = "androidx.navigation:navigation-fragment:$version"
        }

        object Security {
            private const val version = "1.1.0-alpha06"
            const val crypto = "androidx.security:security-crypto-ktx:$version"
        }

        object SavedState {
            private const val version = "2.7.0"
            const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
        }

        object SplashScreen {
            private const val version = "1.0.0"
            const val splashScreen = "androidx.core:core-splashscreen:$version"
        }

        object Pagination {
            private const val version = "3.1.1"
            val pagination = "androidx.paging:paging-runtime:$version"
            val paginationRx = "androidx.paging:paging-rxjava3:$version"
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
        const val glideOkHttp = "com.github.bumptech.glide:okhttp3-integration:${version}@aar"
    }

    object Hilt {
        const val version = "2.48"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
    }

    object RX {
        const val rxJava3 = "io.reactivex.rxjava3:rxjava:3.1.8"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:3.0.2"
        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:3.0.1"
    }

    object Flipper {
        private const val version = "0.245.0"
        const val flipper = "com.facebook.flipper:flipper:$version"
    }

    object SoLoader {
        private const val version = "0.10.5"
        const val soLoader = "com.facebook.soloader:soloader:$version"
    }

    // Unit tests dependencies
//    object JUnit {
//        private const val version = "4.13.2"
//        const val junit = "junit:junit:$version"
//    }

    object JUnitExt {
        private const val version = "1.1.5"
        const val junitExt = "androidx.test.ext:junit:$version"
    }

    object Espresso {
        private const val version = "3.5.1"
        const val espresso = "androidx.test.espresso:espresso-core:$version"
    }

    object Mockk {
        private const val version = "1.13.9"
        const val mockk = "io.mockk:mockk:$version"
    }

    object Junit {
        private const val version = "5.10.1"
        const val junitApi = "org.junit.jupiter:junit-jupiter-api:$version"
        const val junitParams = "org.junit.jupiter:junit-jupiter-params:$version"
        const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:$version"
    }
}