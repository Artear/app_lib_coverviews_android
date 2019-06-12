package com.artear.stevedore.stevedoreviews.repository.impl.deserializer

import com.artear.stevedore.stevedoreitems.repository.getModelObject
import com.artear.stevedore.stevedoreitems.repository.model.style.StyleMargin
import com.artear.stevedore.stevedoreviews.repository.model.StyleBoxItems
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class StyleBoxItemsDeserializer : JsonDeserializer<StyleBoxItems> {

    override fun deserialize(json: JsonElement, typeOfT: Type?,
                             context: JsonDeserializationContext): StyleBoxItems {

        val margin = json.getModelObject("margin", context, StyleMargin::class.java)
        val gap = json.getModelObject("gap", context, Float::class.java)

        return StyleBoxItems(margin, gap)
    }

}
