package ru.aza1rat.marketsimapp.feature.auth.data.mapper

import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginResponseDto
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo

fun LoginResponseDto.toUserInfoDomain(): UserInfo {
    val firstName = checkNotNull(user.firstName) {
        "Обязательное поле 'firstName' равно null"
    }
    return UserInfo(id = user.id, firstName = firstName)
}
