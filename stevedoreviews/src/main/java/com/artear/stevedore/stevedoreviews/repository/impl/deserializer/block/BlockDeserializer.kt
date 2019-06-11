package com.artear.stevedore.stevedoreviews.repository.impl.deserializer.block

import com.artear.stevedore.articleitem.BoxDataArticle
import com.artear.stevedore.stevedoreitems.repository.DeserializerUtil.Companion.getDataFromJson
import com.artear.stevedore.stevedoreitems.repository.DeserializerUtil.Companion.getStyleFromJson
import com.artear.stevedore.stevedoreitems.repository.DeserializerUtil.Companion.getTypeFromJson
import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxData
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
//            BoxType.DFP -> getDataFromJson<BoxDataDfp>(context, json)
//            BlockType.CATEGORY -> getDataFromJson<BlockContentCategory>(context, json)
//            BlockType.MEDIA -> getDataFromJson<BlockContentMedia>(context, json)
            else -> BoxData()
        }
        return Box(type, style, data)
    }

}


