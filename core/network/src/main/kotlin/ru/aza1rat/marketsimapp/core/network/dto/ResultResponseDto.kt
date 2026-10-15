package ru.aza1rat.marketsimapp.core.network.dto

data class ResultResponseDto<T>(
    val success: Boolean,
    val message: String?,
    val data: T?,
    val errors: List<String>?
)
