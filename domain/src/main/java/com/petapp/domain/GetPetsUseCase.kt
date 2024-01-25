package com.petapp.domain

import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Single

interface GetPetsUseCase {
    fun getPets(): Single<Pets>
}