package com.petapp.data.local.mapper

import com.petapp.data.local.model.PetEntity
import com.petapp.domain.pets.model.Pet

class PetToPetEntityMapper : Function1<List<Pet>, List<PetEntity>> {
    override fun invoke(pets: List<Pet>): List<PetEntity> {
        return pets.map { mapPetToEntity(it) }
    }

    private fun mapPetToEntity(pet: Pet): PetEntity {
        return with(pet) {
            PetEntity(
                id = id,
                name = name,
                description = description,
                photos = photos,
                breeds = breeds,
                size = size,
                gender = gender,
                status = status,
                distance = distance
            )
        }
    }
}