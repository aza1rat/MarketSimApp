plugins {
    alias(libs.plugins.convention.android.lib)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.dagger.hilt.android)
}

android {
    namespace = "ru.aza1rat.marketsimapp.core.network"
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.dagger.hilt.android)
    ksp(libs.google.dagger.hilt.android.compiler)
}