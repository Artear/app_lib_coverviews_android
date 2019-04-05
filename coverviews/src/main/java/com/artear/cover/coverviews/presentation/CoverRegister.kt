package com.artear.cover.coverviews.presentation

import com.artear.cover.coveritem.presentation.adapter.ItemAdapter
import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.repository.model.block.Block
import com.artear.cover.coveritem.repository.model.block.BlockType
import com.artear.domain.coroutine.DataShaper


class CoverRegister private constructor() {

    val adapters = listOf<ItemAdapter<*>>()
    val shaperMap = mapOf<BlockType, DataShaper<Block, ArtearItem>>()

    class Builder {

        val list = mutableListOf<Triple<BlockType, DataShaper<Block, ArtearItem>, ItemAdapter<*>>>()
        val coverRegister = CoverRegister()

        fun add(blockType: BlockType, shaper: DataShaper<Block, ArtearItem>, adapter: ItemAdapter<*>) {
            list.add(Triple(blockType, shaper, adapter))
        }

        fun build() {
            if (list.isEmpty())
                throw IllegalStateException("The register must have almost one element")

        }

    }
}