package com.artear.stevedore.stevedoreviews.repository.impl.domain

import com.artear.networking.contract.Networking
import com.artear.networking.model.retrofit.executeWith
import com.artear.stevedore.stevedoreviews.repository.contract.api.ApiStevedore
import com.artear.stevedore.stevedoreviews.repository.contract.domain.StevedoreRepository

class StevedoreRepositoryImpl(private val apiStevedore: ApiStevedore,
                              private val dynamicEndpoint: String,
                              private val networking: Networking) : StevedoreRepository {

    override fun stevedore() = executeWith(networking) {
        apiStevedore.getStevedore(dynamicEndpoint)
    }

}