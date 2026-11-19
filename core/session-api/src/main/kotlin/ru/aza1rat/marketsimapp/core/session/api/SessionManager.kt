package ru.aza1rat.marketsimapp.core.session.api

import ru.aza1rat.marketsimapp.core.session.dto.Session

interface SessionManager {
    suspend fun getSession(): Session?
    suspend fun saveSession(session: Session)
    suspend fun handleUnauthorized(token: String)
}