package com.artear.cover.coveritem.repository.model.media

import com.artear.cover.coveritem.repository.deserializer.media.MediaDeserializer
import com.artear.cover.coveritem.repository.model.DataWrapper

import com.google.gson.annotations.JsonAdapter

@JsonAdapter(MediaDeserializer::class)
data class Media(override val type: MediaType, override val data: MediaContent, override val style: MediaStyle?) :
        DataWrapper<MediaType, MediaContent, MediaStyle?>(type, data, style)