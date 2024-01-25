package com.petapp.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject


class PetsAuthLocalDataStore @Inject constructor(context: Context) {
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

    var accessToken: String?
        get() = sharedPreferences.getString(AUTH_TOKEN_KEY, null)
        set(token) {
            sharedPreferences.edit { putString(AUTH_TOKEN_KEY, token) }
        }

    companion object {
        private const val AUTH_TOKEN_KEY = "auth_token_key"
    }

}