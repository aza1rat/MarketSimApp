package ru.aza1rat.marketsimapp.core.network.dto

import kotlinx.serialization.json.JsonElement

data class FailedResponseDto(
    val type: String?,
    val title: String?,
    val status: Int?,
    val detail: String?,
    val errors: JsonElement?
)
