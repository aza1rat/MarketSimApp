package ru.aza1rat.marketsimapp.feature.tracking.data.mapper

import ru.aza1rat.marketsimapp.feature.tracking.data.dto.OrderItemDto
import ru.aza1rat.marketsimapp.feature.tracking.domain.model.OrderItem

fun OrderItemDto.toOrderItemDomain(): Result<OrderItem> =
    this.productName?.let {
        Result.success(
            OrderItem(
                id = this.id,
                productId = this.productId,
                productName = this.productName,
                quantity = this.quantity,
                totalPrice = this.totalPrice
            )
        )
    } ?: Result.failure(IllegalArgumentException("Название заказанного продукта не определено"))
