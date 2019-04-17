package com.artear.cover.coverviews.repository.impl.provider

import com.artear.cover.coveritem.repository.gson.GsonMaker
import com.artear.cover.coverviews.repository.contract.api.ApiCover
import com.artear.networking.url.BaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCoverProvider(private val baseUrl: BaseUrl, private val gsonMaker: GsonMaker) {

    operator fun invoke(): ApiCover {
        val gson = gsonMaker.makeGsonBuilder().create()
        return Retrofit.Builder()
                .baseUrl(baseUrl.toString())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiCover::class.java)
    }

}