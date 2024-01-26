package com.petapp.details.state

import com.petapp.details.model.PetDetailsItemUiModel

data class PetDetailsUiState(
    val imageUrl: String,
    val name: String,
    val distance: String?,
    val description: String,
    val breed: PetDetailsItemUiModel,
    val size: PetDetailsItemUiModel,
    val gender: PetDetailsItemUiModel,
    val status: String,
)