package com.petapp.domain

import com.petapp.domain.pets.model.Pets
import io.reactivex.rxjava3.core.Observable

interface GetPetsUseCase {
    fun getPets(): Observable<Pets>
}