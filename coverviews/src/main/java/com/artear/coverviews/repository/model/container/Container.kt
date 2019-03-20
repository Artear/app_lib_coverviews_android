package com.artear.coverviews.repository.model.container


import com.artear.coverviews.repository.impl.ContainerDeserializer
import com.artear.coverviews.repository.model.block.Block
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(ContainerDeserializer::class)
data class Container(
        val header: ContainerHeader?,
        val style: ContainerStyle?,
        val items: List<Block>
)