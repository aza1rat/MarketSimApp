package ru.aza1rat.marketsimapp.feature.category.data.mapper

import ru.aza1rat.marketsimapp.feature.category.data.dto.CategoryResponseDto
import ru.aza1rat.marketsimapp.feature.category.domain.model.Category

fun CategoryResponseDto.toCategoryDomain(): Result<Category> =
    this.name?.let {
        Result.success(Category(id = this.id, name = this.name))
    } ?: Result.failure(IllegalArgumentException("Название категории не определено"))
