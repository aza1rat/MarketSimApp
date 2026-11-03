package ru.aza1rat.marketsimapp.feature.auth.register.domain

import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.toRegisterRequest
import ru.aza1rat.marketsimapp.feature.auth.domain.AuthRepository
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo

class RegisterUserAuthUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(credentialsUser: CredentialsUser): Result<UserInfo, AuthError> {
        return repository.register(credentialsUser.toRegisterRequest())
    }
}
