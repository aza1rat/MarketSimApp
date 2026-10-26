package ru.aza1rat.marketsimapp.core.datastore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
    override suspend fun getSession(): Flow<Session?> = flow {
        val sessionTokenJson =
            dataStoreClient.getValue(DataStoreKey.SESSION).first()
        sessionTokenJson?.let {
            val session = Json.decodeFromString<Session>(it)
            if (isTokenExpired(session.expiresAt)) {
                deleteSession().first()
                emit(null)
            } else {
                val decryptedToken = cryptoManager.decrypt(session.token)
                emit(session.copy(token = decryptedToken))
            }
        } ?: emit(null)
    }

    override suspend fun setSession(session: Session): Flow<Result<Boolean>> = flow {
        val encryptedToken = cryptoManager.encrypt(session.token)
        val sessionEncryptedToken = session.copy(token = encryptedToken)
        val jsonSession = Json.encodeToString(sessionEncryptedToken)
        dataStoreClient.saveValue(DataStoreKey.SESSION, jsonSession)
        emit(Result.success(true))
    }.catch { error ->
        emit(Result.failure(error))
    }

    override suspend fun deleteSession(): Flow<Result<Boolean>> = flow {
        dataStoreClient.deleteValue(DataStoreKey.SESSION)
        emit(Result.success(true))
    }.catch { error ->
        emit(Result.failure(error))
    }

    private fun isTokenExpired(expiresAt: Instant): Boolean {
        return (expiresAt <= Clock.System.now())
    }
}
