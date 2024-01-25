package com.petapp.domain.pets

import com.petapp.domain.GetPetsUseCase
import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPetsUseCaseImpl @Inject constructor(val petsRepo: PetsRepo) : GetPetsUseCase {
    override fun getPets(): Single<Pets> {
        return petsRepo.getPets()
    }
}