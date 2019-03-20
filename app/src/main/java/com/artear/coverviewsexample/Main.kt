package com.artear.coverviewsexample

import com.artear.coverviews.GetCover
import com.artear.coverviews.Manager
import com.artear.coverviews.repository.impl.block.BlockDeserializer
import com.artear.coverviews.repository.model.block.Block
import com.artear.coverviews.retrofit.ApiCover
import com.artear.coverviews.retrofit.CoverRepositoryImpl
import com.artear.networking.contract.Networking
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Main {


    fun getApiCover(manager : Manager) : ApiCover {
        val gson = GsonBuilder()
                .registerTypeAdapter(Block::class.javaObjectType, BlockDeserializer(manager))
                .create()
       return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ApiCover::class.java)
    }

    fun onCreate(){


        val manager = Manager()
//        manager.registerTypeDeserializer(BlockType.ARTICLE, BlockContentArticleDeserializer())
//        manager.registerTypeDeserializer(BlockType.ARTICLE, BlockContentArticleDeserializer())
//        manager.registerTypeDeserializer(BlockType.ARTICLE, BlockContentArticleDeserializer())

        val coverApi = getApiCover(manager)

        val coverRepository = CoverRepositoryImpl(coverApi, object : Networking {
            override fun isNetworkConnected(): Boolean {
                return true
            }
        })


        GetCover(coverRepository)

    }




}