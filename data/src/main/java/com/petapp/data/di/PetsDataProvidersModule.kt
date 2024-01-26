package com.petapp.data.di

import android.content.Context
import com.petapp.data.db.PetsDatabase
import com.petapp.data.local.dao.PetsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PetsDataProvidersModule {

    @Provides
    fun providePetsDao(
        @ApplicationContext context: Context,
    ): PetsDao {
        return PetsDatabase.getInstance(context).petsDao()
    }
}