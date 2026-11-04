package ru.aza1rat.marketsimapp.core.ui.text

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource

@Immutable
sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data class StringResource(@field:StringRes val id: Int, val args: Array<Any> = arrayOf()) :
        UiText() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as StringResource

            if (id != other.id) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + args.contentHashCode()
            return result
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource ->
                @Suppress("SpreadOperator")
                stringResource(id, *args)
        }
    }
}
