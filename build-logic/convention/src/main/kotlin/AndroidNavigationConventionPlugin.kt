import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import ru.sequentor.composenavigation.configureAndroidCompose
import ru.sequentor.composenavigation.libs

class AndroidNavigationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("composenavigation.android.library")
            }
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension, false)
            dependencies {
                add("implementation", project(":core:navigation"))

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
            }
        }
    }
}
