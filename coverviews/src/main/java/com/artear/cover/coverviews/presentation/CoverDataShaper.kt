package com.artear.cover.coverviews.presentation

import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.repository.model.block.Block
import com.artear.cover.coveritem.repository.model.block.BlockType
import com.artear.cover.coverviews.repository.model.container.Stevedore
import com.artear.domain.coroutine.DataShaper
import com.artear.tools.android.log.logD

class CoverDataShaper(private val shaperMap: Map<BlockType, DataShaper<Block, ArtearItem>>)
    : DataShaper<Stevedore, List<ArtearItem>> {

    override suspend fun transform(input: Stevedore): List<ArtearItem> {

        val list: MutableList<ArtearItem> = ArrayList()

        input.containers.forEach { container ->
            container.items.forEach {
                try {
                    val artearItem = shaperMap.getValue(it.type).transform(it)
                    list.add(artearItem)
                } catch (e: Exception) {
                    logD { "Error trying to transform block into" }
                }
            }
        }

        return list
    }
}