package ru.aza1rat.marketsimapp.core.network.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

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
        val type: String?,
        val title: String?,
        val status: Int?,
        val detail: String?,
        val errors: JsonElement?
    ) : Response()
}
