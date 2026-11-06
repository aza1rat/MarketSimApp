package ru.aza1rat.marketsimapp.feature.auth.presentation

import ru.aza1rat.marketsimapp.core.ui.R
import ru.aza1rat.marketsimapp.core.ui.text.UiText
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError

fun AuthError.asUiText(): UiText {
    return when (this) {
        AuthError.CredentialsError.INVALID_CREDENTIALS -> UiText.StringResource(
            ru.aza1rat.marketsimapp.feature.auth.R.string.error_invalid_credentials
        )
        AuthError.CredentialsError.USER_ALREADY_EXISTS -> UiText.StringResource(
            ru.aza1rat.marketsimapp.feature.auth.R.string.error_user_already_exists
        )
        AuthError.NetworkError.REQUEST_TIME_OUT -> UiText.StringResource(R.string.error_request_timeout)
        AuthError.NetworkError.CONNECTION -> UiText.StringResource(R.string.error_connection)
        AuthError.ValidationError.INVALID_PASSWORD -> UiText.StringResource(
            ru.aza1rat.marketsimapp.feature.auth.R.string.error_invalid_password
        )
        AuthError.ValidationError.INVALID_LOGIN -> UiText.StringResource(
            ru.aza1rat.marketsimapp.feature.auth.R.string.error_invalid_login
        )
        AuthError.SerializationError -> UiText.StringResource(R.string.error_serialization)
        AuthError.ServerError -> UiText.StringResource(R.string.error_server)
        AuthError.Unauthorized -> UiText.StringResource(R.string.error_unauthorized)
        is AuthError.Unknown -> UiText.DynamicString("$message")
    }
}
