package com.petapp.domain.pets.model

data class Pet(
    val id: Int,
    val organizationId: String,
    val url: String,
    val type: String,
    val species: String,
    val breeds: Breed,
    val colors: Color,
    val age: String,
    val gender: String,
    val size: String,
    val coat: String?,
    val environment: Environment,
    val name: String,
    val description: String,
    val photos: List<Photo>,
    val contact: Contact?,
)

data class Breed(
    val primary: String,
    val secondary: String?,
    val mixed: Boolean,
    val unknown: Boolean
)

data class Color(
    val primary: String?,
    val secondary: String?,
    val tertiary: String?
)

data class Environment(
    val children: Boolean,
    val dogs: Boolean,
    val cats: Boolean
)

data class Photo(
    val small: String,
    val medium: String,
    val large: String,
    val full: String
)

data class Contact(
    val email: String,
    val phone: String,
    val address: Address
)

data class Address(
    val address1: String,
    val address2: String,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String
)
