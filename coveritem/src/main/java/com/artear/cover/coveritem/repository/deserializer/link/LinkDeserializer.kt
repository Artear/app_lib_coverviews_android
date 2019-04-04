package com.artear.cover.coveritem.repository.deserializer.link

import com.artear.cover.coveritem.repository.model.link.Link
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class LinkDeserializer : JsonDeserializer<Link> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Link {
        val url = json.asJsonObject.get("url").asString
        val internal = json.asJsonObject.get("internal").asString
        return Link(url, internal)
    }

}