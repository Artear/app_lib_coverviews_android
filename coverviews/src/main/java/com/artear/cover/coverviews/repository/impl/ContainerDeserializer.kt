package com.artear.cover.coverviews.repository.impl

import com.artear.cover.coveritem.getModelList
import com.artear.cover.coveritem.getModelObject
import com.artear.cover.coverviews.repository.model.block.Block
import com.artear.cover.coverviews.repository.model.container.Container
import com.artear.cover.coverviews.repository.model.container.ContainerHeader
import com.artear.cover.coverviews.repository.model.container.ContainerStyle
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ContainerDeserializer : JsonDeserializer<Container> {


    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Container {

        val header = json.getModelObject("header", context, ContainerHeader::class.java)
        val style = json.getModelObject("style", context, ContainerStyle::class.java)
        val items = json.getModelList("items", context, Block::class)

        return Container(header, style, items)
    }


}


