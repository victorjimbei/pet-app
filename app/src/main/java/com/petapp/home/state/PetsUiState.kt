package com.petapp.home.state

import android.view.View
import com.petapp.home.model.PetUi

sealed class PetsUiState(
    open val isLoading: Boolean = true,
    open val pets: List<PetUi>,
    open val errorMessage: String = "",
    open val emptyPlaceholderVisibility: Int = View.VISIBLE,
) {

    data class LoadingPetsUiState(
        override val isLoading: Boolean = true,
        override val pets: List<PetUi>,
        override val errorMessage: String = "",
        override val emptyPlaceholderVisibility: Int,
    ) : PetsUiState(
        isLoading = isLoading,
        pets = pets,
        errorMessage = errorMessage,
        emptyPlaceholderVisibility = emptyPlaceholderVisibility,
    )

    data class PetsLoadedUiState(
        override val isLoading: Boolean = false,
        override val pets: List<PetUi>,
        override val errorMessage: String = "",
        override val emptyPlaceholderVisibility: Int,
    ) : PetsUiState(
        isLoading = false,
        pets = pets,
        errorMessage = errorMessage,
        emptyPlaceholderVisibility = emptyPlaceholderVisibility,
    )

    data class PetsErrorUiState(
        override val isLoading: Boolean = false,
        override val pets: List<PetUi>,
        override val errorMessage: String = "",
        override val emptyPlaceholderVisibility: Int = View.VISIBLE,
    ) : PetsUiState(
        isLoading = false,
        pets = pets,
        errorMessage = errorMessage,
        emptyPlaceholderVisibility = emptyPlaceholderVisibility,
    )
}