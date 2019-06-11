package com.artear.stevedore.stevedoreviews.repository.contract.domain


import com.artear.stevedore.stevedoreviews.repository.model.Stevedore


interface StevedoreRepository {

    fun stevedore(): Stevedore

}