package com.artear.cover.coveritem.repository.deserializer.media


import com.artear.cover.coveritem.repository.model.media.MediaContentPicture
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MCPictureDeserializer : JsonDeserializer<MediaContentPicture> {

    override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
    ): MediaContentPicture {

        val url = json.asJsonObject.get("url").asString
        val width = json.asJsonObject.get("width").asInt
        val height = json.asJsonObject.get("height").asInt

        return MediaContentPicture(url, width, height)
    }

}
