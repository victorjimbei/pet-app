package com.petapp.home.model

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.petapp.R
import com.petapp.domain.GetPetsUseCase
import com.petapp.home.listener.OnPetClickListener
import com.petapp.home.mapper.PetToPetUiMapper
import com.petapp.home.state.PetsUiState
import com.petapp.util.livedata.NonNullLiveData
import com.petapp.util.livedata.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PetsViewModel @Inject constructor(val app: Application, val getPetsUseCase: GetPetsUseCase) : AndroidViewModel(app), OnPetClickListener {

    private val disposables = CompositeDisposable()
    val uiState = NonNullLiveData<PetsUiState>(PetsUiState.LoadingPetsUiState(true, emptyList(), "", View.VISIBLE))
    val navigateToDetailsScreen = SingleLiveEvent<Void>()

    init {
        getPetsData()
    }

    override fun onPetClicked(petUi: PetUi) {
        // TODO: Implement navigation to details screen
    }

    fun onSwipeToRefresh() {
        uiState.value = PetsUiState.LoadingPetsUiState(
            pets = uiState.value.pets,
            emptyPlaceholderVisibility = if (uiState.value.pets.isEmpty()) View.VISIBLE else View.GONE,
        )
        getPetsData()
    }

    private fun getPetsData() {
        getPetsUseCase.getPets()
            .map { it.pets }
            .map(PetToPetUiMapper())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    uiState.value = PetsUiState.PetsLoadedUiState(
                        pets = it,
                        emptyPlaceholderVisibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                    )
                },
                {
                    uiState.value = PetsUiState.PetsErrorUiState(
                        pets = uiState.value.pets,
                        errorMessage = app.getString(R.string.empty_message)
                    )
                }
            ).let { disposables.add(it) }
    }
}