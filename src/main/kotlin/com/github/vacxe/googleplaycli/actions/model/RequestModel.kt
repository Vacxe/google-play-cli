package com.github.vacxe.googleplaycli.actions.model

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

open class RequestModel(parameters: String? = null) {
    val parameters: Map<String, String> = parameters?.let { it ->
        Json.parseToJsonElement(it).jsonObject
            .toMap()
            .mapValues { it.value.jsonPrimitive.content }
    } ?: emptyMap()
}
