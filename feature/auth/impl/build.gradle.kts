plugins {
    alias(libs.plugins.convention.android.lib)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = "ru.aza1rat.marketsimapp.feature.auth.impl"
}

dependencies {
    implementation(project(":core:mvi"))
    implementation(project(":core:network"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:auth:api"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.google.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    ksp(libs.google.dagger.hilt.android.compiler)
}