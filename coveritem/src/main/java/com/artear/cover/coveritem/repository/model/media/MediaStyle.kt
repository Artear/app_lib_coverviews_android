package com.artear.cover.coveritem.repository.model.media

import com.artear.cover.coveritem.repository.deserializer.media.MediaStyleDeserializer
import com.google.gson.annotations.JsonAdapter


@JsonAdapter(MediaStyleDeserializer::class)
data class MediaStyle(val backgroundColor: String, val usePlaceholder: Boolean)