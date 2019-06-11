package com.artear.stevedore.stevedoreviews.repository.contract.api

import com.artear.stevedore.stevedoreviews.repository.model.Stevedore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiStevedore {

    @GET
    fun getStevedore(@Url dynamicEndpoint: String): Call<Stevedore>

}


