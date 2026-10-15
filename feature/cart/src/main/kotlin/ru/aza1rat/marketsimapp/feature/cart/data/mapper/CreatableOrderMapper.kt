package ru.aza1rat.marketsimapp.feature.cart.data.mapper

import ru.aza1rat.marketsimapp.feature.cart.data.dto.NewOrderItemDto
import ru.aza1rat.marketsimapp.feature.cart.data.dto.NewOrderRequestDto
import ru.aza1rat.marketsimapp.feature.cart.domain.model.CreatableOrder

fun CreatableOrder.toNewOrderRequest(): NewOrderRequestDto {
    val newOrderItems = this.items.map { orderItem ->
        NewOrderItemDto(
            productId = orderItem.productId,
            quantity = orderItem.quantity
        )
    }
    return NewOrderRequestDto(
        shippingAddress = this.shippingAddress,
        notes = null,
        items = newOrderItems
    )
}
