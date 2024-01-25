package com.petapp.data.remote

import com.petapp.data.remote.model.AnimalsDto
import com.petapp.data.remote.model.LoginDto
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

private const val CLIENT_CREDENTIALS = "client_credentials"

// TODO:
private const val CLIENT_ID = "hb8k0LViFXXBg8aqYnqkzffJGvQIxVrVRj8eKx3MgqINmO1b2F"
private const val CLIENT_SECRET = "lsGHh9H7zewMtAYXNfmVo311Qce28TYmet384I4k"

class PetsRemoteDataSource @Inject constructor(val petsApi: PetsApi) {

    fun getToken(clientId: String, clientSecret: String): Single<LoginDto> {
        return petsApi.fetchToken(
            grantType = CLIENT_CREDENTIALS,
            clientId = clientId,
            clientSecret = clientSecret
        ).subscribeOn(Schedulers.io())
    }

    fun getAnimals(token: String, page: Int): Single<AnimalsDto> {
        return petsApi.fetchAnimals(token, page)
    }
}