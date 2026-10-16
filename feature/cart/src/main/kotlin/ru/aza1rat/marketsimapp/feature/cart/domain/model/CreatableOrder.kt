package ru.aza1rat.marketsimapp.feature.cart.domain.model

data class CreatableOrder(
    val shippingAddress: String,
    val items: List<CreatableOrderItem>
)
