package ru.aza1rat.marketsimapp.core.datastore.crypto

import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import kotlin.io.encoding.Base64

class CryptoManager(
    private val keyProvider: CipherKeyProvider,
    private val transformation: String
) {
    fun encrypt(plainText: String): String {
        val blob = plainText.toByteArray()
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, keyProvider.getKey())
        val encryptedBlob = cipher.iv + cipher.doFinal(blob)
        return Base64.encode(encryptedBlob)
    }

    fun decrypt(encryptedText: String): String {
        val encryptedBlob = Base64.decode(encryptedText)
        val iv = encryptedBlob.copyOfRange(0, GCM_IV_LENGTH)
        val encrypted = encryptedBlob.copyOfRange(GCM_IV_LENGTH, encryptedBlob.size)
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, keyProvider.getKey(), GCMParameterSpec(GCM_AUTH_TAG_LENGTH, iv))
        val decryptedBlob = cipher.doFinal(encrypted)
        return decryptedBlob.decodeToString()
    }

    companion object {
        private const val GCM_IV_LENGTH = 12
        private const val GCM_AUTH_TAG_LENGTH = 128
    }
}
