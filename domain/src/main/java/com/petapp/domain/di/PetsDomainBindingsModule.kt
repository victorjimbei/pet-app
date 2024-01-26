package com.petapp.domain.di

import com.petapp.domain.GetPetUseCase
import com.petapp.domain.GetPetsUseCase
import com.petapp.domain.pets.GetPetUseCaseImpl
import com.petapp.domain.pets.GetPetsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PetsDomainBindingsModule {
    @Binds
    abstract fun bindGetPetsUseCase(
        getPetsUseCaseImpl: GetPetsUseCaseImpl
    ): GetPetsUseCase

    @Binds
    abstract fun bindGetPetUseCase(
        getPetUseCaseImpl: GetPetUseCaseImpl
    ): GetPetUseCase
}