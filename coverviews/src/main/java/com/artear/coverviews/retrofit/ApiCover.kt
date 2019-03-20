package com.artear.coverviews.retrofit

import com.artear.coverviews.repository.model.container.Stevedore
import retrofit2.Call
import retrofit2.http.GET

interface ApiCover {

    @GET("cover")
    fun getCover(): Call<Stevedore>

}


