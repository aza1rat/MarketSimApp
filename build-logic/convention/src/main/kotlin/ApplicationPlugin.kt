import com.android.build.api.dsl.ApplicationExtension
import model.ProjectParameters.JAVA_VERSION
import model.ProjectParameters.SDK_COMPILE
import model.ProjectParameters.SDK_MIN
import model.RequiredPluginsId.APPLICATION_ID
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ApplicationPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply(APPLICATION_ID)
        target.extensions.configure<ApplicationExtension>{
            compileSdk {
                version = release(SDK_COMPILE)
            }
            defaultConfig {
                minSdk = SDK_MIN
                targetSdk = SDK_COMPILE
            }
            compileOptions {
                sourceCompatibility = JAVA_VERSION
                targetCompatibility = JAVA_VERSION
            }
            buildFeatures {
                compose = true
            }
        }
    }
}