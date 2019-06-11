package com.artear.stevedore.stevedoreviews.repository.impl.deserializer

import com.artear.stevedore.stevedoreitems.repository.getModelList
import com.artear.stevedore.stevedoreitems.repository.getModelObject
import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreviews.repository.model.Container
import com.artear.stevedore.stevedoreviews.repository.model.ContainerHeader
import com.artear.stevedore.stevedoreviews.repository.model.ContainerStyle
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ContainerDeserializer : JsonDeserializer<Container> {


    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Container {

        val header = json.getModelObject("header", context, ContainerHeader::class.java)
        val style = json.getModelObject("style", context, ContainerStyle::class.java)
        val items = json.getModelList("items", context, Box::class, toleranceError = true)

        return Container(header, style, items)

    }


}


