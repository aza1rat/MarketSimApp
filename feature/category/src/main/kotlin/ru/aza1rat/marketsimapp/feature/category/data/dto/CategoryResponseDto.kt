package ru.aza1rat.marketsimapp.feature.category.data.dto

import kotlin.time.Instant

data class CategoryResponseDto(
    val id: Int,
    val name: String?,
    val description: String?,
    val productCount: Int,
    val createdAt: Instant
)
