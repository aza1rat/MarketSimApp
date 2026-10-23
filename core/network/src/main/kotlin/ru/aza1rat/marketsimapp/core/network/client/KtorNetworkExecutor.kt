package ru.aza1rat.marketsimapp.core.network.client

import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.KSerializer
import ru.aza1rat.marketsimapp.core.network.dto.Response
import ru.aza1rat.marketsimapp.core.network.dto.toResponse
import javax.inject.Inject

class KtorNetworkExecutor @Inject constructor(
    private val clientHolder: HttpClientHolder,
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
        val client = if (request.isAuthorized) {
            clientHolder.authClient
        } else {
            clientHolder.publicClient
        }

        return when (request.method) {
            HttpMethod.GET -> client.get(BASE_URL) {
                url { request.path }
            }

            HttpMethod.POST -> client.post(BASE_URL) {
                url { request.path }
                contentType(ContentType.Application.Json)
                request.body?.let { setBody(it) }
            }
        }
    }
}

private const val BASE_URL = "http://127.0.0.1:5000/"
