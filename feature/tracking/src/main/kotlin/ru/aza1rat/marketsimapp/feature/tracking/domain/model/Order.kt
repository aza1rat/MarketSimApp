package ru.aza1rat.marketsimapp.feature.tracking.domain.model

data class Order(
    val id: Int,
    val totalAmount: Double,
    val status: Int,
    val statusName: String?,
    val shippingAddress: String?,
    val items: List<OrderItem>?
)
