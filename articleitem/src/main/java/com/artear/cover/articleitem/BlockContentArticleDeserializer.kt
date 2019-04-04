package com.artear.cover.articleitem

import com.artear.cover.coveritem.repository.getSafeModelObject
import com.artear.cover.coveritem.repository.model.link.Link
import com.artear.cover.coveritem.repository.model.media.Media
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class BlockContentArticleDeserializer : JsonDeserializer<BlockContentArticle> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext):
            BlockContentArticle {

        val id = json.asJsonObject.get("id").asInt
        val title = json.asJsonObject.get("title").asString
        val description = json.asJsonObject.get("description").asString
        val link = json.getSafeModelObject("link", context, Link::class.java)
        val media = json.getSafeModelObject("media", context, Media::class.java)

        return BlockContentArticle(id, title, description, link, media)
    }
}
