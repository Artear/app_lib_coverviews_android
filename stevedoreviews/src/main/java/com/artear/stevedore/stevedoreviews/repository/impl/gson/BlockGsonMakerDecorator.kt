package com.artear.stevedore.stevedoreviews.repository.impl.gson

import com.artear.stevedore.stevedoreitems.repository.gson.GsonMaker
import com.artear.stevedore.stevedoreitems.repository.gson.GsonMakerDecorator
import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreviews.repository.impl.deserializer.block.BlockDeserializer
import com.google.gson.GsonBuilder


class BlockGsonMakerDecorator(gsonMaker: GsonMaker) : GsonMakerDecorator(gsonMaker) {

    override fun makeGsonBuilder(): GsonBuilder {
        val gsonBuilder = gsonMaker.makeGsonBuilder()
        gsonBuilder.registerTypeAdapter(Box::class.java, BlockDeserializer())
        return gsonBuilder
    }
}