package com.artear.stevedoreviewsexample

import com.artear.domain.coroutine.SimpleReceiver
import com.artear.domain.coroutine.UseCase
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreviews.GetStevedore
import com.artear.stevedore.stevedoreviews.repository.ApiAction
import kotlinx.coroutines.Deferred


class RecipesUseCase(private val recipes: Recipes, private val recipesByCategory: RecipesByCategory,
                     private val getStevedore: GetStevedore) : UseCase<Void, List<ArtearItem>>() {

    private var deferredByCategory: Deferred<List<ArtearItem>>? = null

    override suspend fun execute(param: Void?): List<ArtearItem> {
        val action = ApiAction(recipes, param)
        //opt 1 - action from constructor , action.param = param
        // opt 2 - two use case for each function. getStevedore config a repository with api custom
        return getStevedore.execute(action)
    }

    fun getRecipesByCategory(idCategory: Int, receiver: SimpleReceiver<List<ArtearItem>>) {
        deferredByCategory?.cancel()
        deferredByCategory = executeAsync(receiver) {
            val action = ApiAction(recipesByCategory, idCategory.toString())
            getStevedore.execute(action)
        }
    }

    fun disposeUseCase() {
        deferredByCategory?.cancel()
    }


}