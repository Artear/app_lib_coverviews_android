package com.artear.stevedoreviewsexample

import com.artear.stevedore.stevedoreviews.repository.ApiType
import retrofit2.Call


data class Cover(val coverApi: CoverApi) : ApiType {
    override fun <T> getUrl(param: T?) = coverApi.getCover().apiUrl()
}

data class Recipes(val recipesApi: RecipesApi) : ApiType {
    override fun <T> getUrl(param: T?) = recipesApi.getRecipes().apiUrl()
}

data class RecipesByCategory(val recipesApi: RecipesApi) : ApiType {
    override fun <T> getUrl(param: T?) =
            recipesApi.getRecipesByCategory(param.toString()).apiUrl()
}

fun <T> Call<T>.apiUrl() = request().url().url().toString()
