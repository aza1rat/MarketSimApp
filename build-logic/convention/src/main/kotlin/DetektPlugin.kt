import io.gitlab.arturbosch.detekt.Detekt
import model.ProjectParameters.DETEKT_JVM_TARGET
import model.RequiredDependencies.DETEKT_COMPOSE_RULES_ALIAS
import model.RequiredDependencies.DETEKT_CONFIGURATION_NAME
import model.RequiredDependencies.DETEKT_FORMATTING_ALIAS
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register

class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libs = target.extensions.getByType<VersionCatalogsExtension>().named("libs")
        target.dependencies {
            add(DETEKT_CONFIGURATION_NAME, libs.findLibrary(DETEKT_FORMATTING_ALIAS).get())
            add(DETEKT_CONFIGURATION_NAME, libs.findLibrary(DETEKT_COMPOSE_RULES_ALIAS).get())
        }
        target.tasks.register<Detekt>("detektMe") {
            setup(target)
            autoCorrect = false
        }
        target.tasks.register<Detekt>("detektCorrect") {
            setup(target)
            autoCorrect = true
        }
    }

    private fun Detekt.setup(project: Project) {
        source = project.files(project.projectDir).asFileTree
        config.setFrom(project.rootProject.files(CONFIG_PATH))
        jvmTarget = DETEKT_JVM_TARGET
        exclude("**/build/**", "**/resources/**", "**/generated/**")
        include("**/*.kt")
        reports {
            html.required.set(true)
            xml.required.set(true)
            txt.required.set(false)
            sarif.required.set(false)
            md.required.set(false)
        }
        parallel = true
        allRules = false
        ignoreFailures = false
        buildUponDefaultConfig = true
    }

    companion object {
        const val CONFIG_PATH = "build-logic/convention/config/detekt-config.yml"
    }
}
