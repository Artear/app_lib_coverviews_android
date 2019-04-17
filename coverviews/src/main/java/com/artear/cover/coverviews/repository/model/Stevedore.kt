package com.artear.cover.coverviews.repository.model

import com.artear.cover.coverviews.repository.impl.deserializer.StevedoreDeserializer
import com.google.gson.annotations.JsonAdapter


@JsonAdapter(StevedoreDeserializer::class)
data class Stevedore(val containers: List<Container>)
