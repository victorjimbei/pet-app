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