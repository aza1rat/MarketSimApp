package ru.aza1rat.marketsimapp.core.network.dto

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend inline fun <reified T> HttpResponse.toResponse(): Response {
    return if (status.isSuccess()) {
        body<Response.ResultResponseDto<T>>()
    } else {
        body<Response.FailedResponseDto>()
    }
}
