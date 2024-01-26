package com.petapp.home.model

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.petapp.R
import com.petapp.domain.GetPetsUseCase
import com.petapp.domain.pets.model.Pagination
import com.petapp.home.listener.OnPetClickListener
import com.petapp.home.mapper.PetToPetUiMapper
import com.petapp.home.state.PetsUiState
import com.petapp.util.livedata.NonNullLiveData
import com.petapp.util.livedata.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

private const val INITIAL_PAGE = 1

class PetsViewModel @Inject constructor(val app: Application, val getPetsUseCase: GetPetsUseCase) : AndroidViewModel(app), OnPetClickListener {
    private val TAG = PetsViewModel::class.java.simpleName
    private val disposables = CompositeDisposable()
    private var pagination: Pagination? = null
    val uiState = NonNullLiveData<PetsUiState>(PetsUiState.LoadingPetsUiState(true, emptyList(), "", View.VISIBLE))
    val navigateToDetailsScreen = SingleLiveEvent<Int>()

    init {
        getPetsData(INITIAL_PAGE)
    }

    override fun onPetClicked(petUi: PetUi) {
        navigateToDetailsScreen.value = petUi.id
    }

    fun onSwipeToRefresh() {
        uiState.value = PetsUiState.LoadingPetsUiState(
            pets = uiState.value.pets,
            emptyPlaceholderVisibility = if (uiState.value.pets.isEmpty()) View.VISIBLE else View.GONE,
        )
        getPetsData(INITIAL_PAGE)
    }

    private fun getPetsData(page: Int) {
        getPetsUseCase.getPets(page)
            .doOnNext { pagination = it.pagination }
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
                    Log.w(TAG, "Failed to load pets: ${it.stackTraceToString()}", it)
                    uiState.value = PetsUiState.PetsErrorUiState(
                        pets = uiState.value.pets,
                        errorMessage = app.getString(R.string.empty_message)
                    )
                }
            ).let { disposables.add(it) }
    }
}