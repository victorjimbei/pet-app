package com.petapp.domain.pets

import com.petapp.domain.pets.model.Pet
import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface PetsRepo {
    fun getPets(page: Int): Single<Pets>
    fun getPet(petId: Int): Single<Pet>
}