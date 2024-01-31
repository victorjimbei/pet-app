package com.petapp.data.remote

import com.petapp.data.remote.mapper.AnimalDtoToPetMapper
import com.petapp.data.remote.model.LoginDto
import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

private const val CLIENT_CREDENTIALS = "client_credentials"
const val INITIAL_PAGE = 1
const val PAGE_SIZE = 20

class PetsRemoteDataSource @Inject constructor(val petsApi: PetsApi) {

    fun getAuthToken(clientId: String, clientSecret: String): Single<LoginDto> {
        return petsApi.fetchToken(
            grantType = CLIENT_CREDENTIALS,
            clientId = clientId,
            clientSecret = clientSecret
        ).subscribeOn(Schedulers.io())
    }

    fun getAnimals(token: String, page: Int = INITIAL_PAGE, limit: Int = PAGE_SIZE): Single<Pets> {
        return petsApi.fetchAnimals(token, page, limit)
            .map(AnimalDtoToPetMapper())
            .subscribeOn(Schedulers.io())
    }
}