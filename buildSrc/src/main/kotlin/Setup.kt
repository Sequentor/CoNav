import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType

private fun BaseExtension.setupAndroid() {
    compileSdkVersion(34)
    defaultConfig {
        minSdk = 21
        targetSdk = 34

        versionCode = 1
        versionName = "0.1"
    }
}

fun Project.setupModuleForAndroidxCompose() {
    val androidExtension: BaseExtension = extensions.findByType<LibraryExtension>()
        ?: extensions.findByType<com.android.build.gradle.AppExtension>()
        ?: error("Could not found Android application or library plugin applied on module $name")

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    val composeCompilerVersion = libs.findVersion("composeCompiler").get().requiredVersion

    androidExtension.apply {
        setupAndroid()

        buildFeatures.apply {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = composeCompilerVersion
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}
