package com.artear.stevedoreviewsexample

import com.artear.stevedore.stevedoreviews.repository.model.Stevedore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RecipesApi {

    @GET("cucinaretv")
    fun getRecipes(@QueryMap queryParams: Map<String, String>): Call<Stevedore>

    @GET("recipes/{category}")
    fun getRecipesByCategory(@Path("category") category: String,
                             @QueryMap queryParams: Map<String, String>): Call<Stevedore>
}