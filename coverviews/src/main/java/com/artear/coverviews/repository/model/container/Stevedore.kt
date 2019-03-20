package com.artear.coverviews.repository.model.container

import com.artear.coverviews.repository.impl.StevedoreDeserializer
import com.google.gson.annotations.JsonAdapter


@JsonAdapter(StevedoreDeserializer::class)
data class Stevedore(val containers: List<Container>)
