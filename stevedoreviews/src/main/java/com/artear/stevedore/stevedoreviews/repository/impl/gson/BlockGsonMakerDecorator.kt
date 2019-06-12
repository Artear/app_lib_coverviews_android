/*
 * Copyright 2019 Artear S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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