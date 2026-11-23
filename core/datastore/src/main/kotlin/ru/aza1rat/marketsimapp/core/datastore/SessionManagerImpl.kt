package ru.aza1rat.marketsimapp.core.datastore

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.aza1rat.marketsimapp.core.session.api.SessionManager
import ru.aza1rat.marketsimapp.core.session.api.SessionStorage
import ru.aza1rat.marketsimapp.core.session.dto.Session
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(private val sessionStorage: SessionStorage) :
    SessionManager {
    private val mutex = Mutex()

    override suspend fun getSession(): Session? {
        return sessionStorage.observeSession().first()
    }

    override suspend fun saveSession(session: Session) {
            sessionStorage.setSession(session).getOrThrow()

    }

    override suspend fun handleUnauthorized(token: String) {
        mutex.withLock {
            val currentSession = sessionStorage.observeSession().first()

            if (currentSession?.token != token) {
                return
            }

            sessionStorage.deleteSession().getOrThrow()
        }
    }
}
