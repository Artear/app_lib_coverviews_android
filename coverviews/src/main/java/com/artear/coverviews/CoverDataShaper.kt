package com.artear.coverviews

import com.artear.coverviews.repository.model.ArtearItem
import com.artear.coverviews.repository.model.container.Stevedore
import com.artear.domain.coroutine.DataShaper

class CoverDataShaper : DataShaper<Stevedore, List<ArtearItem>> {

    override suspend fun transform(input: Stevedore): List<ArtearItem> = DataShapeHelper.containerItemShaper(input)
}