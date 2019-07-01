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
package com.artear.stevedore.stevedoreviews.repository.impl.deserializer

import com.artear.stevedore.stevedoreitems.repository.getModelList
import com.artear.stevedore.stevedoreitems.repository.getSafeModelObject
import com.artear.stevedore.stevedoreviews.repository.model.Container
import com.artear.stevedore.stevedoreviews.repository.model.Paging
import com.artear.stevedore.stevedoreviews.repository.model.Stevedore
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import timber.log.Timber
import java.lang.reflect.Type

class StevedoreDeserializer : JsonDeserializer<Stevedore> {

    companion object {
        const val CONTAINERS = "containers"
        const val PAGING = "paging"
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Stevedore {
        Timber.d("Start Stevedore Deserialize")
        val containers = json.getModelList(CONTAINERS, context, Container::class)
        val paging = json.getSafeModelObject(PAGING, context, Paging::class.java)
        return Stevedore(containers, paging)
    }

}


