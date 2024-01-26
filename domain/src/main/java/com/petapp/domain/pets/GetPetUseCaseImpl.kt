package com.petapp.domain.pets

import com.petapp.domain.GetPetUseCase
import com.petapp.domain.pets.model.Pet
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPetUseCaseImpl @Inject constructor(val petsRepo: PetsRepo) : GetPetUseCase {

    override fun getPet(petId: Int): Single<Pet> {
        return petsRepo.getPet(petId)
    }
}