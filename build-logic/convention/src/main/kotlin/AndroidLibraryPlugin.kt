import com.android.build.api.dsl.LibraryExtension
import model.ProjectParameters.JAVA_VERSION
import model.ProjectParameters.SDK_COMPILE
import model.ProjectParameters.SDK_MIN
import model.RequiredPluginsId.ANDROID_LIBRARY_ID
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply(ANDROID_LIBRARY_ID)
        target.extensions.configure<LibraryExtension> {
            compileSdk {
                version = release(SDK_COMPILE)
            }
            defaultConfig {
                minSdk = SDK_MIN
            }
            compileOptions {
                sourceCompatibility = JAVA_VERSION
                targetCompatibility = JAVA_VERSION
            }
        }
    }
}
