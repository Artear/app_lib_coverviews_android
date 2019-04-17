package com.artear.cover.coverviews.repository.impl.gson

import com.artear.cover.coveritem.repository.gson.GsonMaker
import com.artear.cover.coveritem.repository.gson.GsonMakerDecorator
import com.artear.cover.coveritem.repository.model.block.Block
import com.artear.cover.coverviews.repository.impl.deserializer.block.BlockDeserializer
import com.google.gson.GsonBuilder


class BlockGsonMakerDecorator(gsonMaker: GsonMaker) : GsonMakerDecorator(gsonMaker) {

    override fun makeGsonBuilder(): GsonBuilder {
        val gsonBuilder = gsonMaker.makeGsonBuilder()
        gsonBuilder.registerTypeAdapter(Block::class.java, BlockDeserializer())
        return gsonBuilder
    }
}