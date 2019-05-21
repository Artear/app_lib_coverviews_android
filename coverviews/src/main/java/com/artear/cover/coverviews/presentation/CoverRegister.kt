package com.artear.cover.coverviews.presentation

import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.repository.model.block.Block
import com.artear.cover.coveritem.repository.model.block.BlockType
import com.artear.domain.coroutine.DataShaper


class CoverRegister private constructor() {

    val shaperMap = mutableMapOf<BlockType, DataShaper<Block, ArtearItem>>()

    class Builder {

        private val coverRegister = CoverRegister()

        fun add(blockType: BlockType, shaper: DataShaper<Block, ArtearItem>) = apply {
            coverRegister.shaperMap[blockType] = shaper
        }

        fun build(): CoverRegister {
            if (coverRegister.shaperMap.isEmpty())
                throw IllegalStateException("The register must have almost one element")

            return coverRegister
        }

    }
}