package ru.aza1rat.marketsimapp.core.network.client

data class NetworkRequest(
    val method: HttpMethod,
    val path: String,
    val isAuthorized: Boolean,
    val body: Any?
)

enum class HttpMethod {
    GET, POST
}
