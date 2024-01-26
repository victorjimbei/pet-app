package com.petapp.details.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.petapp.details.PetToPetDetailsUiStateMapper
import com.petapp.details.fragment.PetDetailsFragmentArgs
import com.petapp.details.state.PetDetailsUiState
import com.petapp.domain.GetPetUseCase
import com.petapp.util.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class PetDetailsViewModel @Inject constructor(val getPetUseCase: GetPetUseCase, private val state: SavedStateHandle) : ViewModel() {
    private val TAG = PetDetailsViewModel::class.java.simpleName
    private val args = PetDetailsFragmentArgs.fromSavedStateHandle(state);
    private val petId = args.petId
    private var disposable: Disposable? = null

    val petDetailsUiState = MutableLiveData<PetDetailsUiState>()
    val navigateBack = SingleLiveEvent<Void>()
    val showErrorToast = SingleLiveEvent<Void>()

    init {
        getPetUseCase.getPet(petId)
            .map(PetToPetDetailsUiStateMapper())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { petDetailsUiState.value = it },
                {
                    Log.w(TAG, "Failed to retrieve pet's info", it)
                    showErrorToast.call()
                }
            ).let { disposable = it }
    }

    fun onBackClicked() {
        navigateBack.call()
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}