plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.detekt.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("convention-android-lib") {
            id = "convention.android.lib"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("convention-kotlin") {
            id = "convention.kotlin"
            implementationClass = "KotlinPlugin"
        }
        register("convention-application") {
            id = "convention.application"
            implementationClass = "ApplicationPlugin"
        }
        register("convention-detekt") {
            id = "convention.detekt"
            implementationClass = "DetektPlugin"
        }
    }
}