plugins {
    id("composenavigation.android.library")
    id("composenavigation.android.hilt")
    id("composenavigation.android.library.compose")
}

android {
    namespace = "ru.sequentor.composenavigation.core.navigation"
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.navigation.compose)
}
