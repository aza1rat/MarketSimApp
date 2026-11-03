package ru.aza1rat.marketsimapp.core.network.client

import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import ru.aza1rat.marketsimapp.core.network.dto.Response

@Suppress("SwallowedException")
suspend fun <T> safeApiCall(
    request: suspend () -> HttpResponse,
    parse: suspend (HttpResponse) -> T,
): NetworkResult<T> {
    return try {
        val networkResult = request()
        val statusCode = networkResult.status.value
        when (val data = parse(networkResult)) {
            is Response.ResultResponseDto<*> -> NetworkResult.Success(
                data = data,
                statusCode = statusCode,
                errors = data.message
            )

            is Response.FailedResponseDto -> {
                NetworkResult.HttpError(
                    response = data,
                    statusCode = statusCode
                )
            }

            else -> error(UNEXPECTED_TYPE_MESSAGE)
        }
    } catch (e: HttpRequestTimeoutException) {
        NetworkResult.Timeout
    } catch (e: SerializationException) {
        NetworkResult.SerializationError
    } catch (e: IOException) {
        NetworkResult.ConnectionProblems
    }
}

private const val UNEXPECTED_TYPE_MESSAGE = "Unexpected result type"
