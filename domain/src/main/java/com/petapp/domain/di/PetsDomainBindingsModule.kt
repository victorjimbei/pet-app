package com.petapp.domain.di

import com.petapp.domain.GetPetsUseCase
import com.petapp.domain.pets.GetPetsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class PetsDomainBindingsModule {
    @Binds
    abstract fun bindPetsUseCase(
        petsUseCaseImpl: GetPetsUseCaseImpl
    ): GetPetsUseCase
}