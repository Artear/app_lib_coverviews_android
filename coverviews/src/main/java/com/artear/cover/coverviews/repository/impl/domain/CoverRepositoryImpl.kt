package com.artear.cover.coverviews.repository.impl.domain

import com.artear.cover.coverviews.repository.contract.api.ApiCover
import com.artear.cover.coverviews.repository.contract.domain.CoverRepository
import com.artear.networking.contract.Networking
import com.artear.networking.model.retrofit.executeWith

class CoverRepositoryImpl(private val apiCover: ApiCover,
                          private val dynamicEndpoint: String,
                          private val networking: Networking) : CoverRepository {

    override fun cover() = executeWith(networking) {
        apiCover.getCover(dynamicEndpoint)
    }

}