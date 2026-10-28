package ru.aza1rat.marketsimapp.core.network.client

import kotlinx.serialization.KSerializer
import ru.aza1rat.marketsimapp.core.network.dto.Response

interface NetworkExecutor {
    suspend fun <T> execute(
        request: NetworkRequest,
        serializer: KSerializer<T>
    ): NetworkResult<Response>
}
