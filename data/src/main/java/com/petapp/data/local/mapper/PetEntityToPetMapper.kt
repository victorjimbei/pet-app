package com.petapp.data.local.mapper

import com.petapp.data.local.model.PetEntity
import com.petapp.domain.pets.model.Pet

class PetEntityToPetMapper : Function1<PetEntity, Pet> {
    override fun invoke(entity: PetEntity): Pet {
        return with(entity) {
            Pet(
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