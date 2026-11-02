package ru.aza1rat.marketsimapp.core.network.dto

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

fun List<String>?.toErrorsString() = orEmpty().joinToString("\n")

fun JsonElement.toErrorMessages(path: String = ""): List<String>{
    return when(this){
        is JsonObject -> entries.flatMap { (key, value) ->
            val currentPath = if (path.isEmpty()) key else "$path.$key"
            value.toErrorMessages(currentPath)
        }

        is JsonArray -> flatMap { it.toErrorMessages(path) }

        is JsonPrimitive -> listOfNotNull(contentOrNull?.let{
            if(path.isEmpty()) it else "$path: $it"
        })

        JsonNull -> emptyList()
    }
}
