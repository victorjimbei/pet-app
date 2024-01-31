package com.petapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petapp.data.local.model.PetEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface PetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pets: List<PetEntity>): Completable

    @Query("DELETE FROM ${PetEntity.TABLE_NAME}")
    fun deleteAllPets(): Completable

    @Query("SELECT * FROM ${PetEntity.TABLE_NAME} ORDER BY id ASC")
    fun getAllPets(): Observable<List<PetEntity>>

    @Query("SELECT * FROM ${PetEntity.TABLE_NAME} WHERE id = :id")
    fun getPet(id: Int): Single<PetEntity>
}