package com.petapp.data.local.mapper

import com.petapp.data.local.model.PetEntity
import com.petapp.domain.pets.model.Pet

class PetToPetEntityMapper : Function1<Pet, PetEntity> {
    override fun invoke(pet: Pet): PetEntity {
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