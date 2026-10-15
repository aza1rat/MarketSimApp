package ru.aza1rat.marketsimapp.feature.tracking.data.dto

data class OrderItemDto(
    val id: Int,
    val productId: Int,
    val productName: String?,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double
)
