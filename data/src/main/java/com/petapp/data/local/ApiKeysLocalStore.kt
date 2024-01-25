package com.petapp.data.local

import javax.inject.Inject

class ApiKeysLocalStore @Inject constructor() {

    init {
        try {
            System.loadLibrary("native-lib")
        } catch (ex: UnsatisfiedLinkError) {
            //ignore
        }
    }

    external fun getClientId(): String
    external fun getClientSecret(): String
}