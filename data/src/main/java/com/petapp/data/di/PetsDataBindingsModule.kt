package com.petapp.data.di

import com.petapp.data.PetsRepoImpl
import com.petapp.domain.pets.PetsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PetsDataBindingsModule {

    @Binds
    abstract fun bindPetsRepo(
        petsRepoImpl: PetsRepoImpl
    ): PetsRepo
}