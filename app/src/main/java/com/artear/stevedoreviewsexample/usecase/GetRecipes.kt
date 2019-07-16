package com.artear.stevedoreviewsexample.usecase

import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreviews.GetStevedore
import com.artear.stevedore.stevedoreviews.PagingUseCase
import com.artear.stevedore.stevedoreviews.presentation.PageParam
import com.artear.stevedore.stevedoreviews.repository.model.Paging


class GetRecipes(private val getStevedore: GetStevedore) : PagingUseCase<Void, List<ArtearItem>>() {

    override suspend fun execute(param: PageParam<Void>?): Pair<List<ArtearItem>, Paging?> {
        return getStevedore.execute(param)
    }

}