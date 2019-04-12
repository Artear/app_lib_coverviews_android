package com.artear.cover.coverviews.repository.retrofit

import com.artear.cover.coverviews.repository.contract.CoverRepository
import com.artear.networking.contract.Networking
import com.artear.networking.model.retrofit.executeWith

class CoverRepositoryImpl(private val apiCover: ApiCover,
                          private val dynamicEndpoint: String,
                          private val networking: Networking) : CoverRepository {

    override fun cover() = executeWith(networking) {
        apiCover.getCover(dynamicEndpoint)
    }

}