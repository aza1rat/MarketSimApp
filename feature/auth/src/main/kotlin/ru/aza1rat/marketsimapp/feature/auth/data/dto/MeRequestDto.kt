package ru.aza1rat.marketsimapp.feature.auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MeRequestDto(val me: Boolean = true)
