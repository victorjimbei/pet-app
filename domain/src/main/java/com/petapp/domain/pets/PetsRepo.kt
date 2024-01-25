package com.petapp.domain.pets

import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Single

interface PetsRepo {
    fun getPets(): Single<Pets>
}