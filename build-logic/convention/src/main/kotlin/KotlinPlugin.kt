import model.ProjectParameters.JAVA_VERSION
import model.ProjectParameters.JVM_TARGET
import model.RequiredPluginsId.KOTLIN_ID
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class KotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply(KOTLIN_ID)
        target.extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JAVA_VERSION
            targetCompatibility = JAVA_VERSION
        }
        target.extensions.configure<KotlinJvmProjectExtension> {
            compilerOptions {
                jvmTarget.set(JVM_TARGET)
            }
        }
    }
}
