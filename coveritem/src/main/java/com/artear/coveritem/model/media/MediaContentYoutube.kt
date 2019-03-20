package com.artear.coveritem.model.media

import com.artear.coveritem.deserializer.media.MCYoutubeDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(MCYoutubeDeserializer::class)
data class MediaContentYoutube(
        val image: MediaContentPicture, val id: String,
        val url: String
) : MediaContent()