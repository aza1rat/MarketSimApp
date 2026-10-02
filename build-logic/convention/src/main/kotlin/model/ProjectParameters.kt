package model

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object ProjectParameters {
    const val SDK_COMPILE = 37
    const val SDK_MIN = 28
    const val DETEKT_JVM_TARGET = "17"
    val JAVA_VERSION: JavaVersion = JavaVersion.VERSION_17
    val JVM_TARGET: JvmTarget = JvmTarget.JVM_17
}