pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "composeNavigation"
include(":app")

include(":core:common")
include(":core:navigation")
include(":core:ui")

include(":feature:feature_one:ui")
include(":feature:feature_two:ui")
include(":feature:feature_three:ui")
include(":feature:feature_four:ui")

include(":feature:start:ui")
