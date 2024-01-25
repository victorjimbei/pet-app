package com.petapp.home.state

import com.petapp.home.model.PetUi

sealed class PetsUiState(
    open val isLoading: Boolean = true,
    open val pets: List<PetUi>,
    open val errorMessage: String = "",
) {

    data class LoadingPetsUiState(
        override val isLoading: Boolean = true,
        override val pets: List<PetUi>,
        override val errorMessage: String = ""
    ) : PetsUiState(
        isLoading = isLoading,
        pets = pets,
        errorMessage = errorMessage,
    )

    data class PetsLoadedUiState(
        override val isLoading: Boolean = false,
        override val pets: List<PetUi>,
        override val errorMessage: String = ""
    ) : PetsUiState(
        isLoading = false,
        pets = pets,
        errorMessage = errorMessage,
    )

    data class PetsErrorUiState(
        override val isLoading: Boolean = false,
        override val pets: List<PetUi>,
        override val errorMessage: String = ""
    ) : PetsUiState(
        isLoading = false,
        pets = pets,
        errorMessage = errorMessage,
    )
}