package ru.aza1rat.marketsimapp.core.network.exception

class AuthorizationRequiredException(message: String = "Для выполнения запроса требуется авторизация. Отсутствует токен") :
    Exception(message)