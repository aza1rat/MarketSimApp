package ru.aza1rat.marketsimapp.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.KSerializer
import ru.aza1rat.marketsimapp.core.network.dto.Response
import ru.aza1rat.marketsimapp.core.network.dto.toResponse
import ru.aza1rat.marketsimapp.core.network.exception.AuthorizationRequiredException
import ru.aza1rat.marketsimapp.core.network.exception.UnauthorizedException
import ru.aza1rat.marketsimapp.core.session.api.SessionManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorNetworkExecutor @Inject constructor(
    private val client: HttpClient,
    private val sessionManager: SessionManager
) : NetworkExecutor {
    override suspend fun <T> execute(
        request: NetworkRequest,
        serializer: KSerializer<T>
    ): NetworkResult<Response> {
        return safeApiCall(
            request = { buildRequest(request) },
            parse = { it.toResponse(serializer = serializer) }
        )
    }

    private suspend fun buildRequest(request: NetworkRequest): HttpResponse {
        val token = if (request.isAuthorized) {
            sessionManager.getSession()?.token ?: throw AuthorizationRequiredException()
        } else {
            null
        }
        val response = when (request.method) {
            HttpMethod.GET -> client.get("$BASE_URL${request.path}") {
                configureAuth(token)
            }

            HttpMethod.POST -> client.post("$BASE_URL${request.path}") {
                configureAuth(token)
                contentType(ContentType.Application.Json)
                request.body?.let { setBody(it) }
            }
        }

        if (token != null && response.status == HttpStatusCode.Unauthorized) {
            sessionManager.handleUnauthorized(token)
            throw UnauthorizedException()
        }

        return response
    }

    private fun HttpRequestBuilder.configureAuth(token: String?) {
        token?.let {
            header(HttpHeaders.Authorization, "Bearer $token")
        }
    }
}

private const val BASE_URL = "http://127.0.0.1:5000/"
