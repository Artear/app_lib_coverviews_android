package com.artear.cover.coverviews.repository.contract.domain


import com.artear.cover.coverviews.repository.model.Stevedore


interface CoverRepository {

    fun cover(): Stevedore

}