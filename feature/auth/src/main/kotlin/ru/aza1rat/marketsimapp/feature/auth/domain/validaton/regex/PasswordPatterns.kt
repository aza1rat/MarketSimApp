package ru.aza1rat.marketsimapp.feature.auth.domain.validaton.regex

private const val PASSWORD_LETTER = "[^\\W\\d_]"
private const val PASSWORD_DIGIT = "\\d"
private const val PASSWORD_SPECIAL_CHAR = "[^\\w\\s]"
private const val PASSWORD_ALLOWED_CHARS = "[\\p{L}\\p{N}\\p{P}\\p{S}\\p{Zs}]"

private const val PASSWORD_HAS_LENGTH = "(?=.{8,32}$)"
private const val PASSWORD_HAS_LETTER = "(?=.*$PASSWORD_LETTER)"
private const val PASSWORD_HAS_DIGIT = "(?=.*$PASSWORD_DIGIT)"
private const val PASSWORD_HAS_SPECIAL_CHAR = "(?=.*$PASSWORD_SPECIAL_CHAR)"

@Suppress("MaxLineLength")
const val PASSWORD_PATTERN = "^$PASSWORD_HAS_LENGTH$PASSWORD_HAS_LETTER$PASSWORD_HAS_DIGIT$PASSWORD_HAS_SPECIAL_CHAR$PASSWORD_ALLOWED_CHARS$PASSWORD_HAS_LENGTH"
