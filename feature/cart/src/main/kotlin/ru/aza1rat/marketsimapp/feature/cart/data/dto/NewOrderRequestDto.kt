package ru.aza1rat.marketsimapp.feature.cart.data.dto

data class NewOrderRequestDto(
    val shippingAddress: String?,
    val notes: String?,
    val items: List<NewOrderItemDto>
)
