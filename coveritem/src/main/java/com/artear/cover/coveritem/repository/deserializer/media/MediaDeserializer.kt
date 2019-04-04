package com.artear.cover.coveritem.repository.deserializer.media

import com.artear.cover.coveritem.repository.DeserializerUtil.Companion.getDataFromJson
import com.artear.cover.coveritem.repository.DeserializerUtil.Companion.getStyleFromJson
import com.artear.cover.coveritem.repository.DeserializerUtil.Companion.getTypeFromJson
import com.artear.cover.coveritem.repository.model.media.*
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MediaDeserializer : JsonDeserializer<Media> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Media {

        val type = getTypeFromJson<MediaType>(context, json)

        val data = when (type) {
            MediaType.PICTURE -> getDataFromJson<MediaContentPicture>(context, json)
            MediaType.YOUTUBE -> getDataFromJson<MediaContentYoutube>(context, json)
        }

        val style = getStyleFromJson<MediaStyle>(context, json)

        return Media(type, data, style)
    }

}
