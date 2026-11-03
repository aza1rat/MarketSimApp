package ru.aza1rat.marketsimapp.feature.auth.domain.validaton

import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.regex.LOGIN_PATTERN
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.regex.PASSWORD_PATTERN
import java.util.regex.Pattern

@Suppress("MaxLineLength")
fun String.isLoginValid(): Boolean {
    val pattern = Pattern.compile(LOGIN_PATTERN)

    return pattern.matcher(this).matches()
}

fun String.isPasswordValid(): Boolean {
    val pattern = Pattern.compile(PASSWORD_PATTERN)

    return pattern.matcher(this).matches()
}
