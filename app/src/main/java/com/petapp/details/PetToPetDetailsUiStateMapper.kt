package com.petapp.details

import com.petapp.details.model.PetDetailsItemUiModel
import com.petapp.details.state.PetDetailsUiState
import com.petapp.domain.pets.model.Pet

class PetToPetDetailsUiStateMapper : Function1<Pet, PetDetailsUiState> {
    override fun invoke(pet: Pet): PetDetailsUiState {
        return with(pet) {
            PetDetailsUiState(
                imageUrl = if (photos.isNotEmpty()) photos[0].full else "",
                name = name,
                distance = distance?.toString(),
                description = description,
                breed = PetDetailsItemUiModel(label = "Breed", value = breeds.primary),
                size = PetDetailsItemUiModel(label = "Size", value = size),
                gender = PetDetailsItemUiModel(label = "Gender", value = gender),
                status = status.orEmpty()
            )
        }
    }
}