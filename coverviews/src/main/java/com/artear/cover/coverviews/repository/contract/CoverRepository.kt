package com.artear.cover.coverviews.repository.contract


import com.artear.cover.coverviews.repository.model.Stevedore


interface CoverRepository {

    fun cover(): Stevedore

}