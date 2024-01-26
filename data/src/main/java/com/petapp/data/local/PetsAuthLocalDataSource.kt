package com.petapp.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject


class PetsAuthLocalDataSource @Inject constructor(context: Context) {
    private val masterKey: MasterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            "auth_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun getToken(): String? = sharedPreferences.getString(AUTH_TOKEN_KEY, null)
    fun setToken(token: String) {
        sharedPreferences.edit { putString(AUTH_TOKEN_KEY, token) }
    }

    fun getExpireTime(): Long = sharedPreferences.getLong(AUTH_TOKEN_EXPIRE_TIME, 0)
    fun setExpireTime(expireTime: Long) {
        sharedPreferences.edit { putLong(AUTH_TOKEN_EXPIRE_TIME, expireTime) }
    }

    companion object {
        private const val AUTH_TOKEN_KEY = "auth_token_key"
        private const val AUTH_TOKEN_EXPIRE_TIME = "auth_token"
    }
}