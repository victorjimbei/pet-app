package com.petapp.domain.pets.model

data class Pet(
    val id: Int,
    val name: String,
    val description: String,
    val photos: List<Photo>,
    val breeds: Breed,
    val size: String,
    val gender: String,
    val status: String?,
    val distance: Int?,
)

data class Breed(
    val primary: String,
    val secondary: String?,
    val mixed: Boolean,
    val unknown: Boolean
)

data class Photo(
    val small: String,
    val medium: String,
    val large: String,
    val full: String
)
