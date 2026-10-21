package ru.aza1rat.marketsimapp.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.aza1rat.marketsimapp.core.network.TokenStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    @PublicHttpClient
    fun providePublicClient(): HttpClient =
        HttpClient(CIO) {
            install(HttpTimeout) {
                connectTimeoutMillis = CONNECTION_TIMEOUT_MILLIS
                requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
                socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys })
            }
        }

    @Singleton
    @Provides
    @AuthorizedHttpClient
    fun provideAuthClient(tokenStorage: TokenStorage): HttpClient =
        HttpClient(CIO) {
            install(HttpTimeout) {
                connectTimeoutMillis = CONNECTION_TIMEOUT_MILLIS
                requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
                socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys })
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val token = tokenStorage.token

                        if (token != null) {
                            BearerTokens(
                                accessToken = token,
                                refreshToken = ""
                            )
                        } else {
                            null
                        }
                    }
                }
            }
        }
}

private const val CONNECTION_TIMEOUT_MILLIS = 15_000L
private const val REQUEST_TIMEOUT_MILLIS = 30_000L
private const val SOCKET_TIMEOUT_MILLIS = 30_000L
