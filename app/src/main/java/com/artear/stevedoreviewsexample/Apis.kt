package com.artear.stevedoreviewsexample

import com.artear.stevedore.stevedoreviews.repository.contract.EndpointProvider
import retrofit2.Call


data class CoverEP(val coverApi: CoverApi) : EndpointProvider {
    override fun endpoint(param: Any?) = coverApi.getCover().apiUrl()
}

data class RecipesEP(val recipesApi: RecipesApi) : EndpointProvider {
    override fun endpoint(param: Any?) = recipesApi.getRecipes().apiUrl()
}

data class RecipesByCategoryEP(val recipesApi: RecipesApi) : EndpointProvider {
    override fun endpoint(param: Any?): String {
        checkNotNull(param)
        return recipesApi.getRecipesByCategory(param.toString()).apiUrl()
    }
}

fun <T> Call<T>.apiUrl() = request().url().url().toString()
