package ru.aza1rat.marketsimapp.core.network.dto

fun List<String>?.toErrorsString() = orEmpty().joinToString("\n")
