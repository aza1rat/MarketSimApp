package ru.aza1rat.marketsimapp.feature.tracking.data.dto

import kotlin.time.Instant

data class OrderResponseDto(
    val id: Int,
    val orderNumber: String?,
    val totalAmount: Double,
    val status: Int,
    val statusName: String?,
    val shippingAddress: String?,
    val notes: String?,
    val userId: Int,
    val userFullName: String?,
    val createdAt: Instant,
    val items: List<OrderItemDto>?
)
