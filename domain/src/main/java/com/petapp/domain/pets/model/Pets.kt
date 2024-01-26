package com.petapp.domain.pets.model

data class Pets(
    val pets: List<Pet> = emptyList(),
    val pagination: Pagination? = null
) {
    fun empty(): Pets {
        return Pets(
            pets = listOf(),
            pagination = null
        )
    }
}