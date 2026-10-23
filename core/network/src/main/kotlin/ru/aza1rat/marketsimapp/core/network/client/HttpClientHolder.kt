package ru.aza1rat.marketsimapp.core.network.client

import io.ktor.client.HttpClient
import ru.aza1rat.marketsimapp.core.network.di.AuthorizedHttpClient
import ru.aza1rat.marketsimapp.core.network.di.PublicHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpClientHolder @Inject constructor(
    @PublicHttpClient val publicClient: HttpClient,
    @AuthorizedHttpClient val authClient: HttpClient
) : Closable {
    override fun close() {
        publicClient.close()
        authClient.close()
    }
}
