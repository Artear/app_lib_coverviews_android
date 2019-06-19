package com.artear.stevedoreviewsexample

import com.artear.stevedore.stevedoreviews.repository.model.Stevedore
import retrofit2.Call
import retrofit2.http.GET


interface CoverApi {

    @GET("cover")
    fun getCover(): Call<Stevedore>

}