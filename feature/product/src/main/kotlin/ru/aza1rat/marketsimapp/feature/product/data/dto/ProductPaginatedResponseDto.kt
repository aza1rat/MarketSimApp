package ru.aza1rat.marketsimapp.feature.product.data.dto

data class ProductPaginatedResponseDto(
    val items: List<ProductResponseDto>?,
    val totalCount: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val totalPages: Int,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)
