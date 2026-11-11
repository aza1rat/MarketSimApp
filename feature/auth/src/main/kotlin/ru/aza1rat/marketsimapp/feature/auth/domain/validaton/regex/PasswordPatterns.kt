package ru.aza1rat.marketsimapp.feature.auth.domain.validaton.regex

private const val PASSWORD_LETTER = "\\p{L}"
private const val PASSWORD_DIGIT = "\\p{N}"
private const val PASSWORD_SPECIAL_CHAR = "[\\p{P}\\p{S}]"
private const val PASSWORD_ALLOWED_CHARS = "[\\p{L}\\p{N}\\p{P}\\p{S}\\p{Zs}]"

private const val PASSWORD_HAS_LENGTH = "(?=.{8,32}$)"
private const val PASSWORD_HAS_LETTER = "(?=.*$PASSWORD_LETTER)"
private const val PASSWORD_HAS_DIGIT = "(?=.*$PASSWORD_DIGIT)"
private const val PASSWORD_HAS_SPECIAL_CHAR = "(?=.*$PASSWORD_SPECIAL_CHAR)"

@Suppress("MaxLineLength")
const val PASSWORD_PATTERN = "^$PASSWORD_HAS_LENGTH$PASSWORD_HAS_LETTER$PASSWORD_HAS_DIGIT$PASSWORD_HAS_SPECIAL_CHAR$PASSWORD_ALLOWED_CHARS+$"
