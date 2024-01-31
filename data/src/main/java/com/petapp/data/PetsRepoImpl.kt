package com.petapp.data

import com.petapp.data.local.ApiKeysLocalSource
import com.petapp.data.local.PetsAuthLocalDataSource
import com.petapp.data.local.PetsLocalDataSource
import com.petapp.data.remote.PetsRemoteDataSource
import com.petapp.domain.pets.PetsRepo
import com.petapp.domain.pets.model.Pet
import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PetsRepoImpl @Inject constructor(
    val petsRemoteDataSource: PetsRemoteDataSource,
    val petsLocalDataSource: PetsLocalDataSource,
    val apiKeysLocalSource: ApiKeysLocalSource,
    val authLocalDataSource: PetsAuthLocalDataSource,
) : PetsRepo {
    override fun getPets(page: Int): Single<Pets> {
        return fetchRemotePets(page)
            .flatMap { savePets(it) }
//            .mergeWith(petsLocalDataSource.getAllPets())
            .subscribeOn(Schedulers.io())
    }

    override fun getPet(petId: Int): Single<Pet> {
        return petsLocalDataSource.getPet(petId)
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
        val accessToken = authLocalDataSource.getToken()
        val expireTime = authLocalDataSource.getExpireTime()
        val accessTokenSingle = if (accessToken.isNullOrEmpty() || isTokenExpired(expireTime)) {
            refreshAuthToken()
        } else {
            Single.just(accessToken)
        }
        return accessTokenSingle
    }

    private fun isTokenExpired(expireTime: Long) = System.currentTimeMillis() >= expireTime

    private fun refreshAuthToken(): Single<String> {
        return petsRemoteDataSource.getAuthToken(apiKeysLocalSource.getClientId(), apiKeysLocalSource.getClientSecret())
            .map {
                val newAccessToken = "${it.tokenType} ${it.accessToken}"
                authLocalDataSource.setToken(newAccessToken)
                authLocalDataSource.setExpireTime(System.currentTimeMillis().plus(TimeUnit.SECONDS.toMillis(it.expiresIn)))
                newAccessToken
            }
    }
}