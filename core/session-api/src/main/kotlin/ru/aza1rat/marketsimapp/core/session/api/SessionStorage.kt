package ru.aza1rat.marketsimapp.core.session.api

import kotlinx.coroutines.flow.Flow
import ru.aza1rat.marketsimapp.core.session.dto.Session

interface SessionStorage {
    fun observeSession(): Flow<Session?>
    suspend fun setSession(session: Session): Result<Boolean>
    suspend fun deleteSession(): Result<Boolean>
}
