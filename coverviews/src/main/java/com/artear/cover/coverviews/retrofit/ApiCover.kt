package com.artear.cover.coverviews.retrofit

import com.artear.cover.coverviews.repository.model.container.Stevedore
import retrofit2.Call
import retrofit2.http.GET

interface ApiCover {

    @GET("cover")
    fun getCover(): Call<Stevedore>

}


