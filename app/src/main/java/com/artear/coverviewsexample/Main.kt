package com.artear.coverviewsexample

import com.artear.cover.articleitem.ArticleBlockType
import com.artear.cover.coverviews.GetCover
import com.artear.cover.coverviews.Manager
import com.artear.cover.coverviews.repository.impl.block.BlockDeserializer
import com.artear.cover.coverviews.repository.model.block.Block
import com.artear.cover.coverviews.retrofit.ApiCover
import com.artear.cover.coverviews.retrofit.CoverRepositoryImpl
import com.artear.networking.contract.Networking
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main {


    fun getApiCover(manager: Manager): ApiCover {
        val gson = GsonBuilder()
                .registerTypeAdapter(Block::class.java, BlockDeserializer(manager))
                .create()
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ApiCover::class.java)
    }

    fun onCreate() {


        val manager = Manager()

        manager.registerTypeDeserializer(ArticleBlockType())

        val coverApi = getApiCover(manager)

        val coverRepository = CoverRepositoryImpl(coverApi, object : Networking {
            override fun isNetworkConnected(): Boolean {
                return true
            }
        })


        GetCover(coverRepository)

    }


}