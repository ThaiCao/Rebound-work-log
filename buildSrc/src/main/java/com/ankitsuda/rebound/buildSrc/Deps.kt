package com.ankitsuda.rebound.buildSrc

object Deps {
    object Gradle {
    }

    object Kotlin {
        const val version = "1.5.31"

        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Android {
        private const val gradleVersion = "7.0.3"

        const val gradle = "com.android.tools.build:gradle:$gradleVersion"

        const val activityVersion = "1.4.0"
        const val activityCompose = "androidx.activity:activity-compose:$activityVersion"

        private const val navigationVersion = "2.4.0-beta02"
//        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:2.4.0-beta01"
        const val navigationCompose = "androidx.navigation:navigation-compose:$navigationVersion"
        const val navigationHiltCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"

        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0"

        object Compose {
            const val version = "1.1.0-beta02"

            const val ui = "androidx.compose.ui:ui:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val materialDesign = "androidx.compose.material:material:$version"
            const val materialDesignIcons = "androidx.compose.material:material-icons-core:$version"
            const val materialDesignIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-rc01"
            const val liveData = "androidx.compose.runtime:runtime-livedata:$version"
            const val activity = "androidx.activity:activity-compose:$activityVersion"
            const val viewModels = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0"
            const val paging = "androidx.paging:paging-compose:1.0.0-alpha14"

//            private const val lottieVersion = "4.2.0"
//            const val lottie = "com.airbnb.android:lottie-compose:$lottieVersion"

            const val coil = "io.coil-kt:coil-compose:${Utils.coilVersion}"
//            const val reorderable = "org.burnoutcrew.composereorderable:reorderable:0.7.0"
        }

        object Accompanist {
            private const val version = "0.21.2-beta"

            const val insets = "com.google.accompanist:accompanist-insets:$version"
            const val insetsUi = "com.google.accompanist:accompanist-insets-ui:$version"
            const val pager = "com.google.accompanist:accompanist-pager:$version"
            const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$version"

            //            const val permissions = "com.google.accompanist:accompanist-permissions:$version"
            const val placeholder = "com.google.accompanist:accompanist-placeholder-material:$version"
//            const val swiperefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
            const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$version"
            const val navigationMaterial = "com.google.accompanist:accompanist-navigation-material:$version"
            const val navigationFlowlayout = "com.google.accompanist:accompanist-flowlayout:$version"
        }

        object Lifecycle {
            private const val version = "2.4.0"
            private const val vmSavedStateVersion = "2.4.0"

            const val runtime = "androidx.lifecycle:lifecycle-runtime:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val vmKotlin = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val vmSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$vmSavedStateVersion"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
        }

        object Room {
            private const val roomVersion = "2.4.0-beta01"

            const val compiler = "androidx.room:room-compiler:$roomVersion"
            const val runtime = "androidx.room:room-runtime:$roomVersion"
            const val ktx = "androidx.room:room-ktx:$roomVersion"
            const val paging = "androidx.room:room-paging:$roomVersion"
        }

        object Paging {
            private const val version = "3.1.0-beta01"

            const val common = "androidx.paging:paging-common-ktx:$version"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
        }
    }

    object Utils {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
//        const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.3.1"

        const val junit = "junit:junit:4.13.2"
        const val threeTen = "org.threeten:threetenbp:1.5.1"

        const val coilVersion = "2.0.0-alpha02"
        const val coil = "io.coil-kt:coil:$coilVersion"
    }


    object Dagger {
        private const val version = "2.40"

        const val hiltVersion = "2.40"
        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
        const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    }

    object LeakCanary {
        private const val version = "2.7"

        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$version"
    }

}
