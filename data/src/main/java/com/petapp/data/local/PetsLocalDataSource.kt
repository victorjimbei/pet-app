package com.petapp.data.local

import com.petapp.data.local.dao.PetsDao
import com.petapp.data.local.mapper.PetEntityToPetMapper
import com.petapp.data.local.mapper.PetToPetEntityMapper
import com.petapp.domain.pets.model.Pet
import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

private const val FIRST_PAGE = 1

class PetsLocalDataSource @Inject constructor(val petsDao: PetsDao) {

    fun savePets(pets: Pets): Completable {
        return removeOutdatedCache(pets)
            .andThen(Observable.fromIterable(pets.pets))
            .map(PetToPetEntityMapper())
            .flatMapCompletable { petsDao.setPet(it) }
            .subscribeOn(Schedulers.io())
    }

    private fun removeOutdatedCache(pets: Pets): Completable {
        val removePetsCompletable = if (pets.pagination?.currentPage == FIRST_PAGE) {
            petsDao.deleteAllPets()
        } else {
            Completable.complete()
        }
        return removePetsCompletable
    }

    fun getPet(petId: Int): Single<Pet> {
        return petsDao.getPet(petId)
            .map(PetEntityToPetMapper())
            .subscribeOn(Schedulers.io())
    }

    fun getAllPets(): Observable<Pets> {
        val mapper = PetEntityToPetMapper()
        return petsDao.getAllPetsObservable()
            .map { it.map { mapper.invoke(it) } }
            .map { Pets(pets = it, pagination = null) }
            .subscribeOn(Schedulers.io())
    }
}