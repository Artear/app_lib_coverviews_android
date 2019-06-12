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

import com.artear.stevedore.headeritem.repository.ContainerHeader
import com.artear.stevedore.stevedoreitems.repository.getModelList
import com.artear.stevedore.stevedoreitems.repository.getModelObject
import com.artear.stevedore.stevedoreitems.repository.getSafeModelObject
import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreviews.repository.model.Container
import com.artear.stevedore.stevedoreviews.repository.model.ContainerStyle
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ContainerDeserializer : JsonDeserializer<Container> {


    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Container {

        val header = json.getSafeModelObject("header", context, ContainerHeader::class.java)
        val style = json.getModelObject("style", context, ContainerStyle::class.java)
        val items = json.getModelList("items", context, Box::class, toleranceError = true)

        return Container(header, style, items)

    }


}


