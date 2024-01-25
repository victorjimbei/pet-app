package com.petapp.data.remote.mapper

import com.petapp.data.remote.model.AnimalDto
import com.petapp.data.remote.model.AnimalsDto
import com.petapp.data.remote.model.PaginationDto
import com.petapp.data.remote.model.PhotoDto
import com.petapp.domain.pets.model.Breed
import com.petapp.domain.pets.model.Pagination
import com.petapp.domain.pets.model.Pet
import com.petapp.domain.pets.model.Pets
import com.petapp.domain.pets.model.Photo

class AnimalDtoToPetMapper : Function1<AnimalsDto, Pets> {
    override fun invoke(animals: AnimalsDto): Pets {
        return Pets(
            pets = animals.animals.map { mapAnimalToPet(it) },
            pagination = getPagination(animals.pagination)
        )
    }

    private fun mapAnimalToPet(animalDto: AnimalDto): Pet {
        return with(animalDto) {
            Pet(
                id = id,
                name = name.orEmpty(),
                breeds = mapBreadDtoToBread(),
                size = size.orEmpty(),
                gender = gender.orEmpty(),
                description = description.orEmpty(),
                photos = photos.map { mapPhotoDtoToPhoto(it) },
                status = status,
                distance = distance,
            )
        }
    }

    private fun AnimalDto.mapBreadDtoToBread() =
        Breed(primary = breeds?.primary.orEmpty(), secondary = breeds?.secondary.orEmpty(), mixed = breeds?.mixed ?: false, unknown = breeds?.unknown ?: false)

    private fun mapPhotoDtoToPhoto(it: PhotoDto) =
        Photo(small = it.small.orEmpty(), medium = it.medium.orEmpty(), large = it.large.orEmpty(), full = it.full.orEmpty())

    private fun getPagination(pagination: PaginationDto): Pagination {
        return with(pagination) {
            Pagination(
                countPerPage = currentPage ?: 0,
                totalCount = totalCount ?: 0,
                currentPage = currentPage ?: 0,
                totalPages = totalPages ?: 0,
            )
        }
    }
}