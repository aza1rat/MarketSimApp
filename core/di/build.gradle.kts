plugins {
    alias(libs.plugins.convention.android.lib)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.dagger.hilt.android)
}

android {
    namespace = "ru.aza1rat.marketsimapp.core.di"
}

dependencies {
    implementation(libs.google.dagger.hilt.android)
    ksp(libs.google.dagger.hilt.android.compiler)
    implementation(libs.kotlinx.coroutines.core)
}