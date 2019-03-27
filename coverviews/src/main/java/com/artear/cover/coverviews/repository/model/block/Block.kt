package com.artear.cover.coverviews.repository.model.block

import com.artear.cover.coveritem.model.BlockContent
import com.artear.cover.coverviews.repository.impl.block.BlockDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(BlockDeserializer::class)
data class Block(var type: BlockType, val style: BlockStyle, val data: BlockContent)