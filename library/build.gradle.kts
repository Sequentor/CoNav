plugins {
    id("com.android.library")
    id("kotlin-parcelize")
}

setupModuleForAndroidxCompose()

android {
    namespace = "ru.sequentor.conav"
}

dependencies {
    implementation(libs.compose.compiler)
    implementation(libs.compose.runtime)
    implementation(libs.compose.navigation)
}
