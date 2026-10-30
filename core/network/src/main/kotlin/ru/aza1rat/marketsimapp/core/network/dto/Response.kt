package ru.aza1rat.marketsimapp.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
sealed class Response {
    @Serializable
    data class ResultResponseDto<T>(
        val success: Boolean,
        val message: String?,
        val data: T?,
        val errors: List<String>?
    ) : Response()

    @Serializable
    data class FailedResponseDto(
        val type: String? = null,
        val title: String? = null,
        val status: Int?,
        val detail: String? = null,
        val errors: List<String>?
    ) : Response()
}
