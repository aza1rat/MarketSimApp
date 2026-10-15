package ru.aza1rat.marketsimapp.feature.tracking.data.mapper

import ru.aza1rat.marketsimapp.feature.tracking.data.dto.OrderResponseDto
import ru.aza1rat.marketsimapp.feature.tracking.domain.model.Order

fun OrderResponseDto.toOrderDomain(): Result<Order> {
    val orderedProducts = this.items?.map { orderItemDto ->
        orderItemDto.toOrderItemDomain().getOrElse {
            return Result.failure(
                IllegalArgumentException("При обработке списка заказанных товаров исключение: ${it.message}")
            )
        }
    }

    return orderedProducts?.let {
        Result.success(
            Order(
                id = this.id,
                totalAmount = this.totalAmount,
                status = this.status,
                statusName = this.statusName,
                shippingAddress = this.shippingAddress,
                items = orderedProducts
            )
        )
    } ?: Result.failure(IllegalArgumentException("Список заказанных продуктов отсутствует"))
}
