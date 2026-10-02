// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.google.dagger.hilt.android) apply false
    kotlin(libs.plugins.kotlin.plugin.serialization.get().pluginId) version
            (libs.plugins.kotlin.plugin.serialization.get().version.requiredVersion)
    alias(libs.plugins.detekt)
    alias(libs.plugins.convention.detekt)
}