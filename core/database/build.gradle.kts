plugins {
    alias(libs.plugins.convention.kotlin)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(libs.androidx.room3.runtime)
    ksp(libs.androidx.room3.compiler)
    implementation(libs.kotlinx.coroutines.core)
}