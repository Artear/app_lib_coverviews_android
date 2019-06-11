package com.artear.stevedore.stevedoreviews.repository.model


import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreviews.repository.impl.deserializer.ContainerDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(ContainerDeserializer::class)
data class Container(
        val header: ContainerHeader?,
        val style: ContainerStyle?,
        val items: List<Box>
)