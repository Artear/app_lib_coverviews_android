package com.artear.cover.coverviews

import com.artear.cover.coveritem.model.BlockContent
import com.artear.cover.coveritem.model.BlockType


class Manager {

    private val list = mutableListOf<BlockType>()

    fun registerTypeDeserializer(block: BlockType) {
        list.add(block)
    }

    fun getBlockContentClass(type: BlockType): Class<out BlockContent> {
        return list.first { it.description == type.description }.blockContentClass
    }
}