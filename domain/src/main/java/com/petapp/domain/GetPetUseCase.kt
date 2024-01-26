package com.petapp.domain

import com.petapp.domain.pets.model.Pet
import io.reactivex.rxjava3.core.Single

interface GetPetUseCase {

    fun getPet(petId: Int): Single<Pet>
}