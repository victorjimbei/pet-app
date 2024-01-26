package com.petapp.domain.pets.model

data class Pagination(
    val countPerPage: Int,
    val totalCount: Int,
    val currentPage: Int,
    val totalPages: Int,
)
