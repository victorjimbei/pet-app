package com.petapp.data.remote.mapper

import com.petapp.data.remote.model.AnimalDto
import com.petapp.data.remote.model.AnimalsDto
import com.petapp.data.remote.model.PaginationDto
import com.petapp.data.remote.model.PhotoDto
import com.petapp.domain.pets.model.Address
import com.petapp.domain.pets.model.Breed
import com.petapp.domain.pets.model.Color
import com.petapp.domain.pets.model.Contact
import com.petapp.domain.pets.model.Environment
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
                organizationId = organizationId.orEmpty(),
                url = url.orEmpty(),
                type = type.orEmpty(),
                species = species.orEmpty(),
                breeds = mapBreadDtoToBread(),
                colors = mapColorDtoToColor(),
                age = age.orEmpty(),
                gender = gender.orEmpty(),
                size = size.orEmpty(),
                coat = coat,
                environment = mapEnvironmentDtoToEnvironment(),
                name = name.orEmpty(),
                description = description.orEmpty(),
                photos = photos.map { mapPhotoDtoToPhoto(it) },
                contact = mapContactDtoToContact()

            )
        }
    }

    private fun AnimalDto.mapBreadDtoToBread() =
        Breed(primary = breeds?.primary.orEmpty(), secondary = breeds?.secondary.orEmpty(), mixed = breeds?.mixed ?: false, unknown = breeds?.unknown ?: false)

    private fun AnimalDto.mapColorDtoToColor() = Color(primary = colors?.primary, secondary = colors?.secondary, tertiary = colors?.tertiary)

    private fun AnimalDto.mapEnvironmentDtoToEnvironment() =
        Environment(children = environment?.children ?: false, dogs = environment?.dogs ?: false, cats = environment?.cats ?: false)

    private fun mapPhotoDtoToPhoto(it: PhotoDto) =
        Photo(small = it.small.orEmpty(), medium = it.medium.orEmpty(), large = it.large.orEmpty(), full = it.full.orEmpty())

    private fun AnimalDto.mapContactDtoToContact(): Contact? {
        return if (contact == null) {
            null
        } else {
            with(contact) {
                Contact(
                    email = email.orEmpty(),
                    phone = phone.orEmpty(),
                    address = Address(
                        address1 = address?.address1.orEmpty(),
                        address2 = address?.address2.orEmpty(),
                        city = address?.city.orEmpty(),
                        state = address?.state.orEmpty(),
                        postcode = address?.postcode.orEmpty(),
                        country = address?.country.orEmpty()
                    )
                )
            }
        }
    }

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