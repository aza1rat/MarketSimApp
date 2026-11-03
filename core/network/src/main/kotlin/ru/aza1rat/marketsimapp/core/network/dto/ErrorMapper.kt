package ru.aza1rat.marketsimapp.core.network.dto

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

fun List<String>?.toErrorsString() = orEmpty().joinToString("\n")

fun JsonElement.toErrorMessages(): List<String> {
    val result = mutableListOf<String>()
    val stack = ArrayDeque<Pair<JsonElement, String>>()
    stack.addLast(this to "")

    while (stack.isNotEmpty()) {
        val (element, path) = stack.removeLast()

        when (element) {
            is JsonObject -> addObjectEntries(element, path, stack)
            is JsonArray -> addArrayElements(element, path, stack)
            is JsonPrimitive -> addErrorMessage(element, path, result)
            JsonNull -> Unit
        }
    }

    return result
}

private fun addObjectEntries(
    element: JsonObject,
    path: String,
    stack: ArrayDeque<Pair<JsonElement, String>>
) {
    element.entries.toList().asReversed().forEach { (key, value) ->
        val currentPath = if (path.isEmpty()) key else "$path.$key"
        stack.addLast(value to currentPath)
    }
}

private fun addArrayElements(
    element: JsonArray,
    path: String,
    stack: ArrayDeque<Pair<JsonElement, String>>
) {
    element.toList().asReversed().forEach { stack.addLast(it to path) }
}

private fun addErrorMessage(
    element: JsonPrimitive,
    path: String,
    result: MutableList<String>
) {
    val message = element.contentOrNull ?: return
    result.add(
        if (path.isEmpty()) message else "$path: $message"
    )
}
