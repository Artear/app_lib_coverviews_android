package com.artear.cover.coverviews.repository.retrofit

import com.artear.cover.coveritem.repository.model.block.Block
import com.artear.cover.coverviews.repository.impl.block.BlockDeserializer
import com.artear.networking.url.BaseUrl
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider(private val baseUrl: BaseUrl, private val builder: GsonBuilder? = null) {

    operator fun invoke(): Retrofit {
        val gsonBuilder = builder ?: GsonBuilder()
        val gson = gsonBuilder
                .registerTypeAdapter(Block::class.java, BlockDeserializer::class)
                .create()

        return Retrofit.Builder()
                .baseUrl(baseUrl.toString())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

}