package com.artear.cover.coveritem.deserializer.media

import com.artear.cover.coveritem.model.media.MediaStyle
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MediaStyleDeserializer : JsonDeserializer<MediaStyle> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): MediaStyle {

        val backgroundColor = json.asJsonObject.get("background_color").asString
        val usePlaceholder = json.asJsonObject.get("use_placeholder").asBoolean

        return MediaStyle(backgroundColor, usePlaceholder)
    }

}