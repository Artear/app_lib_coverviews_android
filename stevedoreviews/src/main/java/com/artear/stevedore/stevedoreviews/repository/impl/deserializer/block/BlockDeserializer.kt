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
package com.artear.stevedore.stevedoreviews.repository.impl.deserializer.block


import com.artear.stevedore.articleitem.repository.BoxDataArticle
import com.artear.stevedore.banneritem.repository.BoxDataDfp
import com.artear.stevedore.categoryitem.repository.BoxDataCategory
import com.artear.stevedore.mediaitem.repository.BoxDataMedia
import com.artear.stevedore.rankingitem.repository.BoxDataRanking


import com.artear.stevedore.stevedoreitems.repository.DeserializerUtil.Companion.getDataFromJson
import com.artear.stevedore.stevedoreitems.repository.DeserializerUtil.Companion.getStyleFromJson
import com.artear.stevedore.stevedoreitems.repository.DeserializerUtil.Companion.getTypeFromJson
import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxStyle
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxType
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class BlockDeserializer : JsonDeserializer<Box> {

    override fun deserialize(json: JsonElement, typeOfT: Type?,
                             context: JsonDeserializationContext): Box {

        val style = getStyleFromJson<BoxStyle>(context, json)
        val type = getTypeFromJson<BoxType>(context, json)

        val data = when (type) {
            BoxType.ARTICLE -> getDataFromJson<BoxDataArticle>(context, json)
            BoxType.DFP -> getDataFromJson<BoxDataDfp>(context, json)
            BoxType.CATEGORY -> getDataFromJson<BoxDataCategory>(context, json)
            BoxType.MEDIA -> getDataFromJson<BoxDataMedia>(context, json)
            BoxType.RANKING -> getDataFromJson<BoxDataRanking>(context, json)
            else -> throw IllegalArgumentException("Type $type is not implement yet.")
        }

        return Box(type, style, data)
    }

}


