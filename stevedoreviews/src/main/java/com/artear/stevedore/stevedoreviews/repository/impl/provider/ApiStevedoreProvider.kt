package com.artear.stevedore.stevedoreviews.repository.impl.provider

import com.artear.networking.url.BaseUrl
import com.artear.stevedore.stevedoreitems.repository.gson.GsonMaker
import com.artear.stevedore.stevedoreviews.repository.contract.api.ApiStevedore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiStevedoreProvider(private val baseUrl: BaseUrl, private val gsonMaker: GsonMaker) {

    operator fun invoke(): ApiStevedore {
        val gson = gsonMaker.makeGsonBuilder().create()
        return Retrofit.Builder()
                .baseUrl(baseUrl.toString())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiStevedore::class.java)
    }

}