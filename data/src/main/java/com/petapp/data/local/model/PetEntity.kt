package com.petapp.data.local.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.petapp.data.local.model.PetEntity.Companion.TABLE_NAME
import com.petapp.domain.pets.model.Breed
import com.petapp.domain.pets.model.Photo

@Entity(
    tableName = TABLE_NAME
)
data class PetEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val photos: List<Photo>,
    val breeds: Breed,
    val size: String,
    val gender: String,
    val status: String?,
    val distance: Int?,
) {
    companion object {
        @Ignore
        const val TABLE_NAME = "pets_table"
    }
}

