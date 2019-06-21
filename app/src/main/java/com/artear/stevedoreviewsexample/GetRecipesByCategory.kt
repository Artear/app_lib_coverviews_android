package com.artear.stevedoreviewsexample

import com.artear.domain.coroutine.UseCase
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreviews.GetStevedore


class GetRecipesByCategory(private val getStevedore: GetStevedore) : UseCase<Int, List<ArtearItem>>() {

    override suspend fun execute(param: Int?): List<ArtearItem> {
        return getStevedore.execute(param.toString())
    }

}