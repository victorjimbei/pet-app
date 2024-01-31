package com.petapp.home.data

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.petapp.data.remote.INITIAL_PAGE
import com.petapp.domain.GetPetsUseCase
import com.petapp.domain.pets.model.Pet
import com.petapp.domain.pets.model.Pets
import com.petapp.home.mapper.PetToPetUiMapper
import com.petapp.home.model.PetUi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PetsDataSource @Inject constructor(private val getPetsUseCase: GetPetsUseCase) : RxPagingSource<Int, PetUi>() {
    override fun getRefreshKey(state: PagingState<Int, PetUi>): Int {
        return INITIAL_PAGE
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PetUi>> {
        val currentPage = params.key ?: INITIAL_PAGE
        return getPetsUseCase.getPets(currentPage)
            .map { toLoadResults(it, currentPage) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResults(pets: Pets, currentPage: Int): LoadResult<Int, PetUi> {
        return LoadResult.Page(
            data = mapToPetUi(pets.pets),
            prevKey = if (pets.pagination?.currentPage == INITIAL_PAGE) null else currentPage,
            nextKey = if (pets.pets.isEmpty() || pets.pagination?.totalPages == currentPage) null else currentPage.inc(),
        )
    }

    private fun mapToPetUi(pets: List<Pet>) = PetToPetUiMapper().invoke(pets)
}