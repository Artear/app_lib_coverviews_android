package com.artear.coverviewsexample

import com.artear.cover.coverviews.GetCover
import com.artear.cover.coverviews.Manager
import com.artear.cover.coverviews.repository.retrofit.ApiCover
import com.artear.cover.coverviews.repository.retrofit.CoverRepositoryImpl
import com.artear.networking.contract.Networking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main {


    private fun getApiCover(): ApiCover {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiCover::class.java)
    }

    fun onCreate() {


        val manager = Manager()
        manager.registerTypeDeserializer()

        val coverApi = getApiCover()

        val coverRepository = CoverRepositoryImpl(coverApi, object : Networking {
            override fun isNetworkConnected(): Boolean {
                return true
            }
        })


        GetCover(coverRepository)

    }


}