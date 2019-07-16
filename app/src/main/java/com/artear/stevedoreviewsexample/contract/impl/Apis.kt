package com.artear.stevedoreviewsexample.contract.impl

import com.artear.stevedore.stevedoreviews.repository.contract.EndpointProvider
import com.artear.stevedoreviewsexample.contract.CoverApi
import com.artear.stevedoreviewsexample.contract.RecipesApi
import retrofit2.Call


data class CoverEP(val coverApi: CoverApi) : EndpointProvider {
    override fun endpoint(param: Any?, queryMap: HashMap<String, String>): String {
        return coverApi.getCover(queryMap).apiUrl()
    }
}

data class RecipesEP(val recipesApi: RecipesApi) : EndpointProvider {
    override fun endpoint(param: Any?, queryMap: HashMap<String, String>): String {
        return recipesApi.getRecipes(queryMap).apiUrl()
    }
}

data class RecipesByCategoryEP(val recipesApi: RecipesApi) : EndpointProvider {
    override fun endpoint(param: Any?, queryMap: HashMap<String, String>): String {
        return recipesApi.getRecipesByCategory(param.toString(), queryMap).apiUrl()
    }
}

fun <T> Call<T>.apiUrl() = request().url().url().toString()
