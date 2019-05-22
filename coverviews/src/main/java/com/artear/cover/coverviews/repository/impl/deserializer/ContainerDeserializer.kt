package com.artear.cover.coverviews.repository.impl.deserializer

import com.artear.cover.coveritem.repository.getModelList
import com.artear.cover.coveritem.repository.getModelObject
import com.artear.cover.coveritem.repository.model.block.Block
import com.artear.cover.coverviews.repository.model.Container
import com.artear.cover.coverviews.repository.model.ContainerHeader
import com.artear.cover.coverviews.repository.model.ContainerStyle
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ContainerDeserializer : JsonDeserializer<Container> {


    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Container {

        val header = json.getModelObject("header", context, ContainerHeader::class.java)
        val style = json.getModelObject("style", context, ContainerStyle::class.java)
        val items = json.getModelList("items", context, Block::class, toleranceError = true)

        return Container(header, style, items)

    }


}


