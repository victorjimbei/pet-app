package com.petapp.data

import com.petapp.data.local.ApiKeysLocalStore
import com.petapp.data.local.PetsAuthLocalDataStore
import com.petapp.data.local.PetsLocalDataSource
import com.petapp.data.remote.PetsRemoteDataSource
import com.petapp.domain.pets.PetsRepo
import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PetsRepoImpl @Inject constructor(
    val petsRemoteDataSource: PetsRemoteDataSource,
    val petsLocalDataSource: PetsLocalDataSource,
    val apiKeysLocalStore: ApiKeysLocalStore,
    val authLocalDataStore: PetsAuthLocalDataStore,
) : PetsRepo {
    override fun getPets(): Single<Pets> {
        return fetchRemotePets(1)
            .flatMap { savePets(it) }
            .subscribeOn(Schedulers.io())
    }

    private fun savePets(pets: Pets) = petsLocalDataSource.savePets(pets)
        .andThen(Single.just(pets))

    private fun fetchRemotePets(page: Int) = getAuthToken()
        .flatMap { petsRemoteDataSource.getAnimals(it, page) }
        .onErrorResumeNext {
            refreshAuthToken()
                .flatMap { petsRemoteDataSource.getAnimals(it, page) }
        }

    private fun getAuthToken(): Single<String> {
        val accessToken = authLocalDataStore.accessToken
        val expireTime = authLocalDataStore.tokenExpireTime
        val accessTokenSingle = if (accessToken.isNullOrEmpty() || isTokenExpired(expireTime)) {
            refreshAuthToken()
        } else {
            Single.just(accessToken)
        }
        return accessTokenSingle
    }

    private fun isTokenExpired(expireTime: Long) = System.currentTimeMillis() >= expireTime

    private fun refreshAuthToken(): Single<String> {
        return petsRemoteDataSource.getAuthToken(apiKeysLocalStore.getClientId(), apiKeysLocalStore.getClientSecret())
            .map {
                authLocalDataStore.accessToken = "${it.tokenType} ${it.accessToken}"
                authLocalDataStore.tokenExpireTime = System.currentTimeMillis().plus(TimeUnit.SECONDS.toMillis(it.expiresIn))
                it.accessToken
            }
    }
}