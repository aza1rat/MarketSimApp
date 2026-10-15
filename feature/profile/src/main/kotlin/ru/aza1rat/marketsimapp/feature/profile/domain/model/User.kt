package ru.aza1rat.marketsimapp.feature.profile.domain.model

data class User(
    val id: Int,
    val firstName: String,
    val email: String,
    val phoneNumber: String?,
    val address: String?
)
