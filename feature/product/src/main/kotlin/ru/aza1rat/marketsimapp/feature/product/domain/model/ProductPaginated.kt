package ru.aza1rat.marketsimapp.feature.product.domain.model

data class ProductPaginated(
    val items: List<Product>,
    val totalCount: Int,
    val pageNumber: Int,
    val totalPages: Int,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)
