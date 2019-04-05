package com.artear.cover.coverviews.presentation

import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.presentation.model.ArtearObject
import com.artear.cover.coverviews.repository.model.container.Stevedore
import com.artear.domain.coroutine.DataShaper

class CoverDataShaper(listDataAccepted: List<ArtearObject<*>>) : DataShaper<Stevedore, List<ArtearItem>> {

    override suspend fun transform(input: Stevedore): List<ArtearItem> {

        val list: MutableList<ArtearItem> = ArrayList()

        input.containers.mapIndexed { index, container ->



        }

        return list
    }
}