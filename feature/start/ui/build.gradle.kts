plugins {
    id("composenavigation.android.feature")
    id("composenavigation.android.library.compose")
}

android {
    namespace = "ru.sequentor.composenavigation.feature.start.ui"
}

dependencies {
    implementation(project(":feature:feature_one:ui"))
    implementation(project(":feature:feature_two:ui"))
    implementation(project(":feature:feature_three:ui"))
    implementation(project(":feature:feature_four:ui"))
}
