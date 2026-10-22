package ru.aza1rat.marketsimapp.core.datastore.crypto

import android.security.keystore.KeyProperties.BLOCK_MODE_GCM
import android.security.keystore.KeyProperties.ENCRYPTION_PADDING_NONE
import android.security.keystore.KeyProperties.KEY_ALGORITHM_AES

internal object CryptoParameters {
    internal const val ALGORITHM = KEY_ALGORITHM_AES
    internal const val BLOCK_MODE = BLOCK_MODE_GCM
    internal const val PADDING = ENCRYPTION_PADDING_NONE
    internal const val PROVIDER = "AndroidKeystore"

    internal fun getTransformation(): String {
        return "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }
}
