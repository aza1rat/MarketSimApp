package ru.aza1rat.marketsimapp.feature.tracking.domain.model

data class OrderItem(
    val id: Int,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val totalPrice: Double
)
