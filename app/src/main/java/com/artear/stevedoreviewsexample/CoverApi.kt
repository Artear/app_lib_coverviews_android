package com.artear.stevedoreviewsexample

import com.artear.stevedore.stevedoreviews.repository.model.Stevedore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface CoverApi {

    @GET("cover")
    fun getCover(@QueryMap queryParams: Map<String, String>): Call<Stevedore>

}