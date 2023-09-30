plugins {
    id("composenavigation.android.application")
    id("composenavigation.android.hilt")
}

android {
    defaultConfig {
        applicationId = "ru.sequentor.composenavigation"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            /* no-op */
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    namespace = "ru.sequentor.composenavigation"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))

    implementation(project(":feature:start:ui"))

    implementation(libs.androidx.startup)
}
