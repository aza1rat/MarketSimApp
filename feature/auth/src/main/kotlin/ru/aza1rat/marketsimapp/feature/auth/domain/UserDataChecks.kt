package ru.aza1rat.marketsimapp.feature.auth.domain

import java.util.regex.Pattern

@Suppress("MaxLineLength")
fun String.isLoginValid(): Boolean {
    val loginRegex = """^(?:[a-zA-Zа-яА-ЯёЁ0-9_-]+(?:\.[a-zA-Zа-яА-ЯёЁ0-9_-]+)*|[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@[a-zA-Zа-яА-ЯёЁ]+(?:-[a-zA-Zа-яА-ЯёЁ]+)*(?:\.[a-zA-Zа-яА-ЯёЁ]+(?:-[a-zA-Zа-яА-ЯёЁ]+)*)*\.[a-zA-Zа-яА-ЯёЁ]+)$"""
    val pattern = Pattern.compile(loginRegex)

    return pattern.matcher(this).matches()
}

fun String.isPasswordValid(): Boolean {
    val passwordRegex =
        """^(?=.{8,32}$)(?=.*[^\W\d_])(?=.*\d)(?=.*[^\w\s])[\p{L}\p{N}\p{P}\p{S}\p{Zs}]{8,32}$"""
    val pattern = Pattern.compile(passwordRegex)

    return pattern.matcher(this).matches()
}
