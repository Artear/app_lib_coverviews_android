package com.artear.stevedore.stevedoreviews.repository.impl.provider

import com.artear.stevedore.stevedoreitems.repository.gson.GsonMaker
import com.artear.stevedore.stevedoreitems.repository.gson.SimpleGsonMaker
import com.artear.stevedore.stevedoreitems.repository.gson.SizeGsonMakerDecorator
import com.artear.stevedore.stevedoreviews.repository.impl.gson.BlockGsonMakerDecorator


object ApiStevedoreHelper {

    fun getDefaultGsonMaker(gsonMakerExt: GsonMaker? = null): GsonMaker {
        return BlockGsonMakerDecorator(SizeGsonMakerDecorator(gsonMakerExt ?: SimpleGsonMaker()))
    }
}