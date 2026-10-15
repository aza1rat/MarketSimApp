package ru.aza1rat.marketsimapp.feature.product.data.mapper

import ru.aza1rat.marketsimapp.feature.product.data.dto.ProductResponseDto
import ru.aza1rat.marketsimapp.feature.product.domain.model.Product

fun ProductResponseDto.toProductDomain(): Result<Product> =
    this.name?.let {
        Result.success(
            Product(
                id = this.id,
                name = this.name,
                description = this.description,
                price = this.price,
                stockQuantity = this.stockQuantity,
                imageUrl = this.imageUrl,
                categoryId = this.categoryId,
                categoryName = this.categoryName
            )
        )
    } ?: Result.failure(IllegalArgumentException("Название товара не определено"))
