package ru.aza1rat.marketsimapp.core.network.dto

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

suspend fun <T> HttpResponse.toResponse(serializer: KSerializer<T>): Response {
    val responseBody = bodyAsText()
    if (responseBody.isBlank()) {
        return Response.FailedResponseDto(
            status = status.value,
            errors = listOf(status.description)
        )
    }

    val elem = Json.parseToJsonElement(body())
    val jsonObject = elem.jsonObject
    return if ("success" in jsonObject) {
        Json.decodeFromJsonElement(Response.ResultResponseDto.serializer(serializer), elem)
    } else {
        Json.decodeFromJsonElement(Response.FailedResponseDto.serializer(), elem)
    }
}
