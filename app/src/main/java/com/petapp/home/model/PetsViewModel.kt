package com.petapp.home.model

import androidx.lifecycle.ViewModel
import com.petapp.domain.GetPetsUseCase
import com.petapp.home.listener.OnPetClickListener
import com.petapp.home.mapper.PetToPetUiMapper
import com.petapp.home.state.PetsUiState
import com.petapp.util.livedata.NonNullLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PetsViewModel @Inject constructor(val getPetsUseCase: GetPetsUseCase) : ViewModel(), OnPetClickListener {

    private val disposables = CompositeDisposable()
    val uiState = NonNullLiveData<PetsUiState>(PetsUiState.LoadingPetsUiState(true, emptyList(), ""))

    init {
        getPetsData()
    }

    override fun onPetClicked(petUi: PetUi) {
        // TODO: Implement navigation to details screen
    }

    fun onSwipeToRefresh() {
        uiState.value = PetsUiState.LoadingPetsUiState(pets = uiState.value.pets)
        getPetsData()
    }

    private fun getPetsData() {
        getPetsUseCase.getPets()
            .map { it.pets }
            .map(PetToPetUiMapper())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { uiState.value = PetsUiState.PetsLoadedUiState(pets = it) },
                {
                    uiState.value = PetsUiState.PetsErrorUiState(
                        pets = uiState.value.pets,
                        errorMessage = it.stackTraceToString()
                    )
                }
            ).let { disposables.add(it) }
    }
}