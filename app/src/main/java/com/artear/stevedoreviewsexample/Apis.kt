package com.artear.stevedoreviewsexample

import com.artear.stevedore.stevedoreviews.repository.ApiType
import retrofit2.Call


data class CoverApiType(val coverApi: CoverApi) : ApiType {
    override fun getUrl(param: Any?) = coverApi.getCover().apiUrl()
}

data class RecipesApiType(val recipesApi: RecipesApi) : ApiType {
    override fun getUrl(param: Any?) = recipesApi.getRecipes().apiUrl()
}

data class RecipesByCategoryApiType(val recipesApi: RecipesApi) : ApiType {
    //TODO see param == null ??
    override fun getUrl(param: Any?) = recipesApi.getRecipesByCategory(param.toString()).apiUrl()
}

fun <T> Call<T>.apiUrl() = request().url().url().toString()
