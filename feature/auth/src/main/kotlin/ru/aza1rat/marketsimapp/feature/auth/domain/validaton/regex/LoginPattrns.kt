package ru.aza1rat.marketsimapp.feature.auth.domain.validaton.regex

private const val LOGIN_ALLOWED_CHARS = "[a-zA-Zа-яА-ЯёЁ0-9_-]"
private const val EMAIL_ALLOWED_CHARS = "[a-zA-Z0-9_-]"
private const val DOMAIN_ALLOWED_CHARS = "[a-zA-Zа-яА-ЯёЁ]"

private const val LOGIN_NOT_EMAIL_SEGMENT = "$LOGIN_ALLOWED_CHARS+"
private const val LOGIN_NOT_EMAIL_PATTERN = "$LOGIN_NOT_EMAIL_SEGMENT(?:\\.$LOGIN_NOT_EMAIL_SEGMENT)*"

private const val LOGIN_EMAIL_SEGMENT = "$EMAIL_ALLOWED_CHARS+"
private const val LOGIN_EMAIL_PATTERN = "$LOGIN_EMAIL_SEGMENT(?:\\.$LOGIN_EMAIL_SEGMENT)*"
private const val DOMAIN_SEGMENT = "$DOMAIN_ALLOWED_CHARS+(?:-$DOMAIN_ALLOWED_CHARS+)*"
private const val DOMAIN_PATTERN = "$DOMAIN_SEGMENT(?:\\.$DOMAIN_SEGMENT)*\\.$DOMAIN_ALLOWED_CHARS+"

const val LOGIN_PATTERN = "^(?:$LOGIN_NOT_EMAIL_PATTERN|$LOGIN_EMAIL_PATTERN@$DOMAIN_PATTERN)$"
