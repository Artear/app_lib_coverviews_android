package com.artear.cover.coveritem.deserializer.media

import com.artear.cover.coveritem.getModelObject
import com.artear.cover.coveritem.model.media.MediaContentPicture
import com.artear.cover.coveritem.model.media.MediaContentYoutube
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MCYoutubeDeserializer : JsonDeserializer<MediaContentYoutube> {

    override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
    ): MediaContentYoutube {

        val id = json.asJsonObject.get("id").asString
        val url = json.asJsonObject.get("url").asString
        val image = json.getModelObject("image", context, MediaContentPicture::class.java)

        return MediaContentYoutube(image, id, url)
    }

}
