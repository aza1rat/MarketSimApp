package ru.aza1rat.marketsimapp.core.network.client

import ru.aza1rat.marketsimapp.core.network.dto.Response

interface NetworkClient {
    suspend fun <T> doRequest(dto: Any): NetworkResult<Response>
}
