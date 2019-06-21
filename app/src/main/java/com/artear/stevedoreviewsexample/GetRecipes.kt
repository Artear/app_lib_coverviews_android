package com.artear.stevedoreviewsexample

import com.artear.domain.coroutine.UseCase
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreviews.GetStevedore


class GetRecipes(private val getStevedore: GetStevedore) : UseCase<Void, List<ArtearItem>>() {

    override suspend fun execute(param: Void?): List<ArtearItem> {
        //opt 1 - action from constructor , action.param = param
        // opt 2 - two use case for each function. getStevedore config a repository with api custom
        return getStevedore.execute(param)
    }

}