package com.artear.stevedore.stevedoreviews.repository.model

import com.artear.stevedore.stevedoreviews.repository.impl.deserializer.StevedoreDeserializer
import com.google.gson.annotations.JsonAdapter


@JsonAdapter(StevedoreDeserializer::class)
data class Stevedore(val containers: List<Container>)
