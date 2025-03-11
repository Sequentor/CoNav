buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.8.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.55")
        classpath("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.1.10")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.1.10-1.0.30")
    }
}

apply(plugin = "com.google.devtools.ksp")