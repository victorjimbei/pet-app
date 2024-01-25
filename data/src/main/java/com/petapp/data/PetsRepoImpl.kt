package com.petapp.data

import com.petapp.data.local.ApiKeysLocalStore
import com.petapp.data.local.PetsAuthLocalDataStore
import com.petapp.data.local.PetsLocalDataSource
import com.petapp.data.remote.PetsRemoteDataSource
import com.petapp.data.remote.mapper.AnimalDtoToPetMapper
import com.petapp.domain.pets.PetsRepo
import com.petapp.domain.pets.model.Pet
import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PetsRepoImpl @Inject constructor(
    val petsRemoteDataSource: PetsRemoteDataSource,
    val petsLocalDataSource: PetsLocalDataSource,
    val apiKeysLocalStore: ApiKeysLocalStore,
    val authLocalDataStore: PetsAuthLocalDataStore,
) : PetsRepo {
    override fun getPets(): Single<Pets> {
        val accessToken = authLocalDataStore.accessToken
        val accessTokenSingle = if(accessToken.isNullOrEmpty()){
            refreshAuthToken()
        }else{
            Single.just(accessToken)
        }
        return accessTokenSingle
            .flatMap { petsRemoteDataSource.getAnimals("Bearer $it", 1) }
            .onErrorResumeNext {
                refreshAuthToken()
                    .flatMap { petsRemoteDataSource.getAnimals("Bearer $it", 1) }
            }
            .map(AnimalDtoToPetMapper())
            .subscribeOn(Schedulers.io())
    }

    private fun refreshAuthToken(): Single<String> {
        return petsRemoteDataSource.getToken(apiKeysLocalStore.getClientId(), apiKeysLocalStore.getClientSecret())
            .map {
                authLocalDataStore.accessToken = it.accessToken
                it.accessToken
            }
    }
}