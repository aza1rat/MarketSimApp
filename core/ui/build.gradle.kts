plugins {
    alias(libs.plugins.convention.android.lib)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "ru.aza1rat.marketsimapp.core.ui"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
}