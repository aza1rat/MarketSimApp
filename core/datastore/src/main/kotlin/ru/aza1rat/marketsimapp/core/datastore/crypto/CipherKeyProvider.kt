package ru.aza1rat.marketsimapp.core.datastore.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties.PURPOSE_DECRYPT
import android.security.keystore.KeyProperties.PURPOSE_ENCRYPT
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.inject.Inject

class CipherKeyProvider @Inject constructor(private val keystore: KeyStore) {
    internal fun getKey(): SecretKey {
        val keyEntry = keystore.getEntry(KEYSTORE_ALIAS, null) as? KeyStore.SecretKeyEntry
        return keyEntry?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(CryptoParameters.ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    KEYSTORE_ALIAS,
                    PURPOSE_ENCRYPT or PURPOSE_DECRYPT
                ).setBlockModes(CryptoParameters.BLOCK_MODE)
                    .setEncryptionPaddings(CryptoParameters.PADDING)
                    .setKeySize(CIPHER_KEY_SIZE)
                    .setRandomizedEncryptionRequired(true)
                    .setUserAuthenticationRequired(false)
                    .build()
            )
        }.generateKey()
    }

    companion object {
        private const val KEYSTORE_ALIAS = "aza1rat-MSA-secret"
        private const val CIPHER_KEY_SIZE = 256
    }
}
