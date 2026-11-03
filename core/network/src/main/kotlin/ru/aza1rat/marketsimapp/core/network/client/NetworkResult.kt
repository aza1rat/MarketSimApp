package ru.aza1rat.marketsimapp.core.network.client

import ru.aza1rat.marketsimapp.core.network.dto.Response

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T, val statusCode: Int, val errors: String? = null) : NetworkResult<T>
    data class HttpError(val response: Response.FailedResponseDto, val statusCode: Int) :
        NetworkResult<Nothing>

    data object ConnectionProblems : NetworkResult<Nothing>
    data object Timeout : NetworkResult<Nothing>
    data object SerializationError : NetworkResult<Nothing>
}
