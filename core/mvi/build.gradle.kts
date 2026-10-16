plugins {
    alias(libs.plugins.convention.android.lib)
}

android {
    namespace = "ru.aza1rat.marketsimapp.core.mvi"
}

dependencies {
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.kotlinx.coroutines.android)
}