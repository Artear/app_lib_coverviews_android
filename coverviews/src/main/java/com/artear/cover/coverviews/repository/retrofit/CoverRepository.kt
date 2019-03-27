package com.artear.cover.coverviews.repository.retrofit


import com.artear.cover.coverviews.repository.model.container.Stevedore


interface CoverRepository {

    fun cover(): Stevedore

}