package com.artear.coverviews.repository.model.block


import com.artear.coveritem.model.BlockContent
import com.artear.coveritem.model.BlockType
import com.artear.coverviews.repository.impl.block.BlockDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(BlockDeserializer::class)
data class Block(var type: BlockType, val style: BlockStyle, val data: BlockContent)