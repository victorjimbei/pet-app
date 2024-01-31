package com.petapp.home.state

import android.view.View

sealed class PetsUiState(
    open val isLoading: Boolean = true,
    open val errorMessage: String = "",
    open val emptyPlaceholderVisibility: Int = View.VISIBLE,
) {

    data class LoadingPetsUiState(
        override val isLoading: Boolean = true,
        override val errorMessage: String = "",
        override val emptyPlaceholderVisibility: Int,
    ) : PetsUiState(
        isLoading = isLoading,
        errorMessage = errorMessage,
        emptyPlaceholderVisibility = emptyPlaceholderVisibility,
    )

    data class PetsLoadedUiState(
        override val isLoading: Boolean = false,
        override val errorMessage: String = "",
        override val emptyPlaceholderVisibility: Int,
    ) : PetsUiState(
        isLoading = false,
        errorMessage = errorMessage,
        emptyPlaceholderVisibility = emptyPlaceholderVisibility,
    )

    data class PetsErrorUiState(
        override val isLoading: Boolean = false,
        override val errorMessage: String = "",
        override val emptyPlaceholderVisibility: Int = View.VISIBLE,
    ) : PetsUiState(
        isLoading = false,
        errorMessage = errorMessage,
        emptyPlaceholderVisibility = emptyPlaceholderVisibility,
    )
}