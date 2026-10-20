plugins {
    alias(libs.plugins.convention.android.lib)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.dagger.hilt.android)
}

android {
    namespace = "ru.aza1rat.marketsimapp.core.datastore"
}

dependencies {
    implementation(project(":core:session-api"))
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.google.dagger.hilt.android)
    ksp(libs.google.dagger.hilt.android.compiler)
    implementation(libs.kotlinx.coroutines.core)
}