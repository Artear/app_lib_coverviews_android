package com.artear.stevedoreviewsexample

import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreviews.GetStevedore
import com.artear.stevedore.stevedoreviews.PagingUseCase
import com.artear.stevedore.stevedoreviews.presentation.PageParam
import com.artear.stevedore.stevedoreviews.repository.model.Paging


class GetRecipesByCategory(private val getStevedore: GetStevedore) :
        PagingUseCase<Int, List<ArtearItem>>() {

    override suspend fun execute(param: PageParam<Int>?): Pair<List<ArtearItem>, Paging?> {
        return getStevedore.execute(param)
    }

}