package ru.aza1rat.marketsimapp.feature.auth.login.domain

import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.toLoginRequest
import ru.aza1rat.marketsimapp.feature.auth.domain.AuthRepository
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo

class LoginUserAuthUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(credentialsUser: CredentialsUser): Result<UserInfo, AuthError> {
        return repository.login(credentialsUser.toLoginRequest())
    }
}
