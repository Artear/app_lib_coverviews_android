package com.artear.stevedoreviewsexample

import com.artear.stevedore.stevedoreviews.repository.contract.api.StevedoreApi
import com.artear.stevedore.stevedoreviews.repository.model.Stevedore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipesApi : StevedoreApi{

    @GET("recipes")
    fun getRecipes(): Call<Stevedore>

    @GET("recipes/{category}")
    fun getRecipesByCategory(@Path("category") category: String): Call<Stevedore>
}