package com.artear.cover.coverviews.repository.contract.api

import com.artear.cover.coverviews.repository.model.Stevedore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiCover {

    @GET
    fun getCover(@Url dynamicEndpoint: String): Call<Stevedore>

}


