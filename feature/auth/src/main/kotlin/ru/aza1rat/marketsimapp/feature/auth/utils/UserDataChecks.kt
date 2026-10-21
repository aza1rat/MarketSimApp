package ru.aza1rat.marketsimapp.feature.auth.utils

import java.util.regex.Pattern

fun String.isEmailValid(): Boolean {
    val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    val pattern = Pattern.compile(emailRegex)

    return pattern.matcher(this).matches()
}

fun String.isPasswordWeak(minLength: Int) = length < minLength
fun String.isPasswordLong(maxLength: Int) = length > maxLength

fun validateCredentials(email: String, password: String, minLength: Int, maxLength: Int): Boolean {
    val isEmailValid = email.isEmailValid()
    val isPasswordLengthCorrect = password.isPasswordWeak(minLength) && password.isPasswordLong(maxLength)
    return isEmailValid && isPasswordLengthCorrect
}
