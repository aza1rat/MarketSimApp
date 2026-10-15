package ru.aza1rat.marketsimapp.feature.auth.data.mapper

import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginResponseDto
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo

fun LoginResponseDto.toUserInfoDomain(): Result<UserInfo> = this.user.firstName?.let {
    Result.success(UserInfo(id = this.user.id, firstName = this.user.firstName))
} ?: Result.failure(IllegalArgumentException("Имя не определено"))
