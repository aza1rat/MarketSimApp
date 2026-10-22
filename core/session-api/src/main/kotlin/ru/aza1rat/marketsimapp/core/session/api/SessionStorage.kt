package ru.aza1rat.marketsimapp.core.session.api

import kotlinx.coroutines.flow.Flow
import ru.aza1rat.marketsimapp.core.session.dto.Session

interface SessionStorage {
    suspend fun getSession(): Flow<Session?>
    suspend fun setSession(session: Session): Flow<Result<Boolean>>
    suspend fun deleteSession(): Flow<Result<Boolean>>
}
