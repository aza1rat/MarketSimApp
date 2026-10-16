package ru.aza1rat.marketsimapp.feature.product.domain.model

data class Product(
    val id: Int,
    val name: String,
    val description: String?,
    val price: Double,
    val stockQuantity: Int,
    val imageUrl: String?,
    val categoryId: Int,
    val categoryName: String?
)
