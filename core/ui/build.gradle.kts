plugins {
    id("composenavigation.android.library")
    id("composenavigation.android.library.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "ru.sequentor.composenavigation.core.ui"
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
}
