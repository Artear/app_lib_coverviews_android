package com.artear.stevedore.stevedoreviews.repository.impl.deserializer

import com.artear.stevedore.stevedoreitems.repository.DeserializerUtil.Companion.getTypeFromJson
import com.artear.stevedore.stevedoreitems.repository.getModelObject
import com.artear.stevedore.stevedoreitems.repository.getSafeModelObject
import com.artear.stevedore.stevedoreitems.repository.model.style.StyleBackground
import com.artear.stevedore.stevedoreitems.repository.model.style.StyleMargin
import com.artear.stevedore.stevedoreviews.repository.model.ContainerStyle
import com.artear.stevedore.stevedoreviews.repository.model.ContainerType
import com.artear.stevedore.stevedoreviews.repository.model.StyleBoxItems
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ContainerStyleDeserializer : JsonDeserializer<ContainerStyle> {

    override fun deserialize(json: JsonElement, typeOfT: Type?,
                             context: JsonDeserializationContext): ContainerStyle {

        val type = getTypeFromJson<ContainerType>(context, json)
        val background = json.getSafeModelObject("background", context, StyleBackground::class.java)
        val margin = json.getModelObject("margin", context, StyleMargin::class.java)
        val itemsStyle = json.getModelObject("items", context, StyleBoxItems::class.java)

        return ContainerStyle(type, background, margin, itemsStyle)
    }

}

