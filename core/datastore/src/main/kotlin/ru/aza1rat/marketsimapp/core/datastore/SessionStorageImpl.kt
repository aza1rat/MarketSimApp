package ru.aza1rat.marketsimapp.core.datastore

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import ru.aza1rat.marketsimapp.core.datastore.api.DataStoreClient
import ru.aza1rat.marketsimapp.core.datastore.crypto.CryptoManager
import ru.aza1rat.marketsimapp.core.session.api.SessionStorage
import ru.aza1rat.marketsimapp.core.session.dto.Session
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.Instant

internal class SessionStorageImpl @Inject constructor(
    private val cryptoManager: CryptoManager,
    private val dataStoreClient: DataStoreClient
) : SessionStorage {
    override fun observeSession(): Flow<Session?> =
        dataStoreClient.getValue(DataStoreKey.SESSION).map { sessionTokenJson ->
            sessionTokenJson?.let {
                val session = Json.decodeFromString<Session>(it)
                if (isTokenExpired(session.expiresAt)) {
                    deleteSession().getOrThrow()
                    null
                } else {
                    val decryptedToken = cryptoManager.decrypt(session.token)
                    session.copy(token = decryptedToken)
                }
            }
        }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun setSession(session: Session): Result<Boolean> {
        return try {
            val encryptedToken = cryptoManager.encrypt(session.token)
            val encryptedSession = session.copy(token = encryptedToken)
            val jsonSession = Json.encodeToString(encryptedSession)
            dataStoreClient.saveValue(DataStoreKey.SESSION, jsonSession)
            Result.success(true)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun deleteSession(): Result<Boolean> {
        return try {
            dataStoreClient.deleteValue(DataStoreKey.SESSION)
            Result.success(true)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun isTokenExpired(expiresAt: Instant): Boolean {
        return (expiresAt <= Clock.System.now())
    }
}
