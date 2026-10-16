package ru.aza1rat.marketsimapp.feature.profile.data.mapper

import ru.aza1rat.marketsimapp.feature.profile.data.dto.UserChangingRequestDto
import ru.aza1rat.marketsimapp.feature.profile.domain.model.ChangeableUserData

fun ChangeableUserData.toUserChangingRequest() = UserChangingRequestDto(
    firstName = this.firstName,
    phoneNumber = this.phoneNumber,
    address = this.address
)
