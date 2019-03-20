package com.artear.coveritem.model.media

import com.artear.coveritem.deserializer.media.MediaDeserializer
import com.artear.coveritem.model.DataWrapper

import com.google.gson.annotations.JsonAdapter

@JsonAdapter(MediaDeserializer::class)
data class Media(override val type: MediaType, override val data: MediaContent, override val style: MediaStyle?) :
        DataWrapper<MediaType, MediaContent, MediaStyle?>(type, data, style)