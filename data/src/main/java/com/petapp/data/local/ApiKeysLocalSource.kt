package com.petapp.data.local

interface ApiKeysLocalSource {
    fun getClientId(): String
    fun getClientSecret(): String
}