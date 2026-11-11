package ru.aza1rat.marketsimapp.core.ui.text

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data class StringResource(
        @field:StringRes val id: Int,
        val args: ImmutableList<Any> = emptyList<Any>().toImmutableList()
    ) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource ->
                @Suppress("SpreadOperator")
                stringResource(id, *args.toTypedArray())
        }
    }

    fun asString(resource: Resources): String {
        return when (this) {
            is DynamicString -> value
            is StringResource ->
                @Suppress("SpreadOperator")
                resource.getString(id, *args.toTypedArray())
        }
    }
}
