package ru.aza1rat.marketsimapp.core.network.exception

class UnauthorizedException(message: String = "Сервер отклонил переданный токен (HTTP 401)") : Exception(message)
