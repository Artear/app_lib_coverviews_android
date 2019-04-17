package com.artear.cover.coverviews.repository.impl.provider

import com.artear.cover.coveritem.repository.gson.GsonMaker
import com.artear.cover.coveritem.repository.gson.SimpleGsonMaker
import com.artear.cover.coveritem.repository.gson.SizeGsonMakerDecorator
import com.artear.cover.coverviews.repository.impl.gson.BlockGsonMakerDecorator


object ApiCoverHelper {

    fun getDefaultGsonMaker(gsonMakerExt: GsonMaker? = null): GsonMaker {
        return BlockGsonMakerDecorator(SizeGsonMakerDecorator(gsonMakerExt ?: SimpleGsonMaker()))
    }
}