package com.petapp.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.petapp.domain.pets.model.Breed
import com.petapp.domain.pets.model.Photo

class PetConverters {
    private val gson = Gson()
    private val breedsType = object : TypeToken<Breed>() {}.type
    private val photosType = object : TypeToken<List<Photo>>() {}.type

    @TypeConverter
    fun breedTypeToString(breeds: Breed): String {
        return gson.toJson(breeds, breedsType)
    }

    @TypeConverter
    fun stringToBreed(breed: String): Breed {
        return try {
            gson.fromJson(breed, breedsType)
        } catch (e: IllegalArgumentException) {
            Breed(
                primary = "", secondary = null, mixed = false, unknown = false
            )
        }
    }

    @TypeConverter
    fun toPhotosJson(photos: List<Photo>): String {
        return gson.toJson(photos, photosType)
    }

    @TypeConverter
    fun toPhotoList(photos: String?): List<Photo> {
        return gson.fromJson(photos, photosType)
    }
}