package ru.aza1rat.marketsimapp.core.datastore.api

import kotlinx.coroutines.flow.Flow
import ru.aza1rat.marketsimapp.core.datastore.DataStoreKey

interface DataStoreClient {
    suspend fun saveValue(keyName: DataStoreKey, value: String)
    fun getValue(keyName: DataStoreKey): Flow<String?>
    suspend fun deleteValue(keyName: DataStoreKey)
}
