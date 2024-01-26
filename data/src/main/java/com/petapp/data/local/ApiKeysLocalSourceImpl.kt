package com.petapp.data.local

import javax.inject.Inject

class ApiKeysLocalSourceImpl @Inject constructor() : ApiKeysLocalSource {

    init {
        try {
            System.loadLibrary("native-lib")
        } catch (ex: UnsatisfiedLinkError) {
            //ignore
        }
    }

    external override fun getClientId(): String
    external override fun getClientSecret(): String
}