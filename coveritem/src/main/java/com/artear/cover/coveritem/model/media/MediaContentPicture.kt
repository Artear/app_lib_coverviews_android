package com.artear.cover.coveritem.model.media


import com.artear.cover.coveritem.deserializer.media.MCPictureDeserializer
import com.google.gson.annotations.JsonAdapter


@JsonAdapter(MCPictureDeserializer::class)
data class MediaContentPicture(val url: String, val width: Int, val height: Int) : MediaContent()

