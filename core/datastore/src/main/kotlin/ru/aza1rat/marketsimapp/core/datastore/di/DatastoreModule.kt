package ru.aza1rat.marketsimapp.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.aza1rat.marketsimapp.core.datastore.DataStoreClientImpl
import ru.aza1rat.marketsimapp.core.datastore.SessionStorageImpl
import ru.aza1rat.marketsimapp.core.datastore.api.DataStoreClient
import ru.aza1rat.marketsimapp.core.datastore.crypto.CipherKeyProvider
import ru.aza1rat.marketsimapp.core.datastore.crypto.CryptoManager
import ru.aza1rat.marketsimapp.core.datastore.crypto.CryptoParameters
import ru.aza1rat.marketsimapp.core.session.api.SessionStorage
import java.io.File
import java.security.KeyStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {
    private const val DATASTORE_PATH = "datastore/marketsimapp.preferences_pb"

    @Provides
    @Singleton
    fun provideKeyStore(): KeyStore {
        return KeyStore.getInstance(CryptoParameters.PROVIDER).apply {
            load(null)
        }
    }

    @Provides
    @Singleton
    fun provideDatastore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            File(context.filesDir, DATASTORE_PATH)
        }
    }

    @Provides
    fun provideCryptoManager(keystore: KeyStore): CryptoManager {
        return CryptoManager(
            keyProvider = CipherKeyProvider(keystore),
            transformation = CryptoParameters.getTransformation()
        )
    }

    @Provides
    fun provideDataStoreClient(dataStore: DataStore<Preferences>): DataStoreClient {
        return DataStoreClientImpl(dataStore)
    }

    @Provides
    fun provideSessionStorage(cryptoManager: CryptoManager, dataStoreClient: DataStoreClient): SessionStorage {
        return SessionStorageImpl(
            cryptoManager = cryptoManager,
            dataStoreClient = dataStoreClient
        )
    }
}
