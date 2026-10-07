plugins {
    alias(libs.plugins.convention.android.lib)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.dagger.hilt.android)
}

android {
    namespace = "ru.aza1rat.marketsimapp.feature.category.impl"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":feature:category:api"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.google.dagger.hilt.android)
    ksp(libs.google.dagger.hilt.android.compiler)
}