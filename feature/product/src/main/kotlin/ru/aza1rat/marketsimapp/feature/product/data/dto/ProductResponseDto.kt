package ru.aza1rat.marketsimapp.feature.product.data.dto

import kotlin.time.Instant

data class ProductResponseDto(
    val id: Int,
    val name: String?,
    val description: String?,
    val price: Double,
    val stockQuantity: Int,
    val imageUrl: String?,
    val categoryId: Int,
    val categoryName: String?,
    val createdAt: Instant
)
