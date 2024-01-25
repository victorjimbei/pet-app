package com.petapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class AnimalsDto(
    val animals: List<AnimalDto>,
    val pagination: PaginationDto
)

data class AnimalDto(
    val id: Int,
    @SerializedName("organization_id")
    val organizationId: String?,
    val url: String?,
    val type: String?,
    val species: String?,
    val breeds: BreedDto?,
    val colors: ColorDto?,
    val age: String?,
    val gender: String?,
    val size: String?,
    val coat: String?,
    val attributes: AttributesDto?,
    val environment: EnvironmentDto?,
    val tags: List<String> = emptyList(),
    val name: String?,
    val description: String?,
    val photos: List<PhotoDto> = emptyList(),
    val videos: List<VideoDto> = emptyList(),
    val status: String?,
    val distance: Int?,
    @SerializedName("published_at")
    val publishedAt: String?,
    val contact: ContactDto?,
    @SerializedName("_links")
    val links: LinksDto?
)

data class BreedDto(
    val primary: String,
    val secondary: String?,
    val mixed: Boolean = false,
    val unknown: Boolean = false
)

data class ColorDto(
    val primary: String?,
    val secondary: String?,
    val tertiary: String?
)

data class AttributesDto(
    @SerializedName("spayed_neutered")
    val spayedNeutered: Boolean = false,
    @SerializedName("house_trained")
    val houseTrained: Boolean = false,
    val declawed: Boolean = false,
    @SerializedName("special_needs")
    val specialNeeds: Boolean = false,
    @SerializedName("shots_current")
    val shotsCurrent: Boolean = false
)

data class EnvironmentDto(
    val children: Boolean = false,
    val dogs: Boolean = false,
    val cats: Boolean = false
)

data class PhotoDto(
    val small: String?,
    val medium: String?,
    val large: String?,
    val full: String?
)

data class VideoDto(
    val embed: String?
)

data class ContactDto(
    val email: String?,
    val phone: String?,
    val address: AddressDto?
)

data class AddressDto(
    val address1: String?,
    val address2: String?,
    val city: String?,
    val state: String?,
    val postcode: String?,
    val country: String?
)

data class LinksDto(
    val self: LinkDto?,
    val type: LinkDto?,
    val organization: LinkDto?
)

data class LinkDto(
    val href: String?
)

data class PaginationDto(
    @SerializedName("count_per_page")
    val countPerPage: Int?,
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("_links")
    val links: LinksDto?
)

