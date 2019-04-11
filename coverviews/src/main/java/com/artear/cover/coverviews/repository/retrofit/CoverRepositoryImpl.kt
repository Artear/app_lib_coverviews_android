package com.artear.cover.coverviews.repository.retrofit

import com.artear.cover.coverviews.repository.contract.CoverRepository
import com.artear.networking.contract.Networking
import com.artear.networking.model.retrofit.executeWith
import retrofit2.Retrofit

class CoverRepositoryImpl(private val retrofit: Retrofit,
                          private val coverEndpoint: String,
                          private val networking: Networking) : CoverRepository {

    private val dynamicEndpoint by lazy { retrofit.baseUrl().toString() + coverEndpoint }
    private val coverApi by lazy { retrofit.create(ApiCover::class.java) }

    override fun cover() = executeWith(networking) {
        coverApi.getCover(dynamicEndpoint)
    }

}