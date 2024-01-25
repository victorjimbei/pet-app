package com.petapp.data.di

import com.petapp.data.PetsRepoImpl
import com.petapp.domain.pets.PetsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class PetsDataModule {

    @Binds
    abstract fun bindPetsRepo(
        petsRepoImpl: PetsRepoImpl
    ): PetsRepo
}