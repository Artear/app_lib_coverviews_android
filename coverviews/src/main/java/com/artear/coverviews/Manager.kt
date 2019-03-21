package com.artear.coverviews

import com.artear.coveritem.model.BlockContent
import com.artear.coveritem.model.BlockType


class Manager {

    private val list = mutableMapOf<BlockType, Class<out BlockContent>>()

    fun <T : BlockContent> registerTypeDeserializer(
            block: BlockType, classToSave: Class<T>) {
        list[block] = classToSave
    }

    fun getBlockContentClass(type: BlockType): Class<out BlockContent> {
        return list[type]!!
    }
}