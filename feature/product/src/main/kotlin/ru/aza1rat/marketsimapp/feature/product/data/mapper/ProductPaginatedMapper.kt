package ru.aza1rat.marketsimapp.feature.product.data.mapper

import ru.aza1rat.marketsimapp.feature.product.data.dto.ProductPaginatedResponseDto
import ru.aza1rat.marketsimapp.feature.product.domain.model.ProductPaginated

fun ProductPaginatedResponseDto.toProductPaginatedDomain(): Result<ProductPaginated> {
    val products = this.items?.map { productDto ->
        productDto.toProductDomain().getOrElse {
            return Result.failure(
                IllegalArgumentException("При обработке списка товаров исключение: ${it.message}")
            )
        }
    }
    return products?.let {
        Result.success(
            ProductPaginated(
                items = products,
                totalCount = this.totalCount,
                pageNumber = this.pageNumber,
                totalPages = this.totalPages,
                hasPreviousPage = this.hasPreviousPage,
                hasNextPage = this.hasNextPage
            )
        )
    } ?: Result.failure(IllegalArgumentException("Список продуктов не определен"))
}
