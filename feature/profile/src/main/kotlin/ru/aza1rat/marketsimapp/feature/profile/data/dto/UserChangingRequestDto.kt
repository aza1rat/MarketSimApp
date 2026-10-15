package ru.aza1rat.marketsimapp.feature.profile.data.dto

data class UserChangingRequestDto(
    val firstName: String,
    val lastName: String = "_",
    val phoneNumber: String?,
    val address: String?
)
