package com.artear.cover.coveritem.repository

import com.artear.tools.exception.checkSize
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import timber.log.Timber
import kotlin.reflect.KClass

class DeserializerUtil {

    companion object {

        const val TYPE = "type"
        const val STYLE = "style"
        const val DATA = "data"

        inline fun <reified T> getTypeFromJson(context: JsonDeserializationContext, json: JsonElement):
                T = json.getModelObject(TYPE, context, T::class.java)

        inline fun <reified T> getDataFromJson(context: JsonDeserializationContext, json: JsonElement):
                T = json.getModelObject(DATA, context, T::class.java)

        inline fun <reified T> getStyleFromJson(context: JsonDeserializationContext, json: JsonElement):
                T = json.getModelObject(STYLE, context, T::class.java)
    }
}

/**
 * In case exception occurs, catch exception and return null.
 */
fun <T> JsonElement.getSafeModelObject(
        keyName: String,
        context: JsonDeserializationContext,
        javaClass: Class<T>
): T? {
    return try {
        getModelObject(keyName, context, javaClass)
    } catch (e: Exception) {
        Timber.d("Parsing key: $keyName for ${javaClass.name} - Exception: ${e.message}")
        null
    }
}


fun <T> JsonElement.getModelObject(
        keyName: String,
        context: JsonDeserializationContext,
        javaClass: Class<T>
): T {
    return context.deserialize<T>(asJsonObject.get(keyName), javaClass)
}

fun JsonElement.getJsonArray(arrayName: String): JsonArray {
    return asJsonObject.get(arrayName).asJsonArray
}

fun <T : Any> JsonElement.getModelList(
        keyName: String,
        context: JsonDeserializationContext,
        itemClass: KClass<T>,
        tolerance: Boolean = true,
        minCount: Int = 1
): ArrayList<T> {

    val items = ArrayList<T>()
    getJsonArray(keyName).forEach { itemJson ->
        try {
            val item = context.deserialize<T>(itemJson, itemClass.java)
            items.add(item)
        } catch (e: Exception) {
            Timber.d(e, "Fail parsing item")
            check(tolerance) {
                "Decoding error was detected in ${itemClass.java.name}. " +
                        "Exception: ${e.message}"
            }
        }
    }

    checkSize(items, minCount) {
        "Total of ${items.size} items decoding, but the minimum required " +
                "in key: $keyName is $minCount"
    }

    return items
}