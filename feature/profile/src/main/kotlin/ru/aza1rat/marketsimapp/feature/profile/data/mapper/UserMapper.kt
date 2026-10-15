package ru.aza1rat.marketsimapp.feature.profile.data.mapper

import ru.aza1rat.marketsimapp.feature.profile.data.dto.UserResponseDto
import ru.aza1rat.marketsimapp.feature.profile.domain.model.User

fun UserResponseDto.toUserDomain(): Result<User> {
    var errorMessage = ""
    if (this.firstName == null) {
        errorMessage += "Имя пользователя не определено "
    }
    if (this.email == null) {
        errorMessage += "Почта пользователя не определена"
    }
    return if (errorMessage.isNotEmpty()) {
        Result.success(
            User(
                id = this.id,
                firstName = this.firstName!!,
                email = this.email!!,
                phoneNumber = this.phoneNumber,
                address = this.address
            )
        )
    } else {
        Result.failure(IllegalArgumentException(errorMessage))
    }
}
