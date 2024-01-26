package com.petapp.home.mapper

import com.petapp.domain.pets.model.Pet
import com.petapp.home.model.PetUi

class PetToPetUiMapper : Function1<List<Pet>, List<PetUi>> {
    override fun invoke(pets: List<Pet>): List<PetUi> {
        return pets.map {
            PetUi(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = if (it.photos.isNotEmpty()) {
                    it.photos[0].small
                } else {
                    ""
                }
            )
        }
    }
}