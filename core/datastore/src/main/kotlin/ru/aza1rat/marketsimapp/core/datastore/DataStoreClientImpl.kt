package ru.aza1rat.marketsimapp.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.aza1rat.marketsimapp.core.datastore.api.DataStoreClient
import javax.inject.Inject

internal class DataStoreClientImpl @Inject constructor(private val datastore: DataStore<Preferences>) :
    DataStoreClient {
    override suspend fun saveValue(keyName: DataStoreKey, value: String) {
        datastore.edit { preferences ->
            preferences[stringPreferencesKey(keyName.keyName)] = value
        }
    }

    override fun getValue(keyName: DataStoreKey): Flow<String?> {
        return datastore.data.map { preferences ->
            preferences[stringPreferencesKey(keyName.keyName)]
        }
    }

    override suspend fun deleteValue(keyName: DataStoreKey) {
        datastore.edit { preferences ->
            preferences.remove(stringPreferencesKey(keyName.keyName))
        }
    }
}
