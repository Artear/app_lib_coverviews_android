package com.artear.coverviews.retrofit

import com.artear.networking.contract.Networking
import com.artear.networking.model.retrofit.executeWith

class CoverRepositoryImpl(
        private val coverApi: ApiCover,
        private val networking: Networking
) : CoverRepository {

    override fun cover() = executeWith(networking) {
        coverApi.getCover()
    }

}