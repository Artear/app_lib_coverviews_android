package com.artear.cover.coveritem.model.media

import com.artear.cover.coveritem.deserializer.media.MCYoutubeDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(MCYoutubeDeserializer::class)
data class MediaContentYoutube(
        val image: MediaContentPicture, val id: String,
        val url: String
) : MediaContent()