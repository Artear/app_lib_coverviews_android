package com.artear.stevedore.stevedoreviews.repository.impl.provider

import com.artear.stevedore.stevedoreitems.repository.gson.GsonMaker
import com.artear.stevedore.stevedoreviews.repository.contract.api.StevedoreApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiStevedoreProvider(private val gsonMaker: GsonMaker) {

    operator fun invoke(): StevedoreApi {
        val gson = gsonMaker.makeGsonBuilder().create()
        //Does not have base url because ApiStevedore execute a get with dynamic Url from params.
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(StevedoreApi::class.java)
    }

}