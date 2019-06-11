package com.artear.stevedore.stevedoreviews.presentation

import com.artear.domain.coroutine.DataShaper
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxType
import com.artear.stevedore.stevedoreviews.repository.model.Stevedore

class StevedoreDataShaper(private val shaperMap: Map<BoxType, DataShaper<Box, ArtearItem>>) :
        DataShaper<Stevedore, List<ArtearItem>> {

    override suspend fun transform(input: Stevedore): List<ArtearItem> {

        val list: MutableList<ArtearItem> = mutableListOf()

        input.containers.forEach { container ->
            container.items.forEach {
                if (shaperMap.containsKey(it.type)) {
                    val artearItem = shaperMap.getValue(it.type).transform(it)
                    artearItem?.let {
                        list.add(it)
                    }
                }
            }
        }

        return list
    }
}