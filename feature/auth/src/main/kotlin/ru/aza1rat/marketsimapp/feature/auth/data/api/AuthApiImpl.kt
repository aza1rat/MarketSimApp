package ru.aza1rat.marketsimapp.feature.auth.data.api

import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import ru.aza1rat.marketsimapp.core.network.client.HttpClientHolder
import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.MeRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.RegisterRequestDto
import javax.inject.Inject

class AuthApiImpl @Inject constructor(
    private val clientHolder: HttpClientHolder
) : AuthApi {
    override suspend fun loginUser(loginRequestDto: LoginRequestDto): HttpResponse {
        return clientHolder.publicClient.post(BASE_URL) {
            url {
                protocol = URLProtocol.HTTP
                path(LOGIN_PATH)
            }
            contentType(ContentType.Application.Json)
            setBody(loginRequestDto)
        }
    }

    override suspend fun registerUser(registerRequestDto: RegisterRequestDto): HttpResponse {
        return clientHolder.publicClient.post(BASE_URL) {
            url {
                protocol = URLProtocol.HTTP
                path(REGISTER_PATH)
            }
            contentType(ContentType.Application.Json)
            setBody(registerRequestDto)
        }
    }

    override suspend fun getUserInfo(meRequestDto: MeRequestDto): HttpResponse {
        return clientHolder.authClient.get(BASE_URL) {
            url {
                protocol = URLProtocol.HTTP
                path(ME_PATH)
            }
            contentType(ContentType.Application.Json)
        }
    }

    private companion object {
        const val BASE_URL = "http://127.0.0.1:5000/"
        const val LOGIN_PATH = "api/Auth/login"
        const val REGISTER_PATH = "api/Auth/register"
        const val ME_PATH = "api/Auth/me"
    }
}
