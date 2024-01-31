package com.petapp.home.model

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.petapp.data.remote.PAGE_SIZE
import com.petapp.home.data.PetsDataSource
import com.petapp.home.state.PetsUiState
import com.petapp.util.livedata.NonNullLiveData
import javax.inject.Inject

class PetsViewModel @Inject constructor(val app: Application, val petsDataSource: PetsDataSource) : AndroidViewModel(app), DefaultLifecycleObserver {

    val uiState = NonNullLiveData<PetsUiState>(PetsUiState.LoadingPetsUiState(false, "", View.GONE))
    val pets: LiveData<PagingData<PetUi>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = { petsDataSource }
    ).liveData.cachedIn(viewModelScope)
}