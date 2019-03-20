package com.artear.coverviews.repository.impl.block

import com.artear.coveritem.deserializer.DeserializerUtil.Companion.getStyleFromJson
import com.artear.coveritem.deserializer.DeserializerUtil.Companion.getTypeFromJson
import com.artear.coveritem.model.BlockContent
import com.artear.coveritem.model.BlockType
import com.artear.coverviews.repository.model.block.Block
import com.artear.coverviews.repository.model.block.BlockStyle
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class BlockDeserializer : JsonDeserializer<Block> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Block {

        val style = getStyleFromJson<BlockStyle>(context, json)


        val type = getTypeFromJson<BlockType>(context, json) // TODO types from registered list

        val data = getDataFromJson(context, json)
//        val data = when (type) { // TODO MAGIC
//            BlockType.ARTICLE -> getDataFromJson<BlockContentArticle>(context, json)
//            BlockType.CATEGORY -> getDataFromJson<BlockContentCategory>(context, json)
//            BlockType.MEDIA -> getDataFromJson<BlockContentMedia>(context, json)
//            BlockType.DFP -> getDataFromJson<BlockContentDfp>(context, json)
//        }


        return Block(type, style, data)
    }

    private fun getDataFromJson(context: JsonDeserializationContext, json: JsonElement): BlockContent {
        return BlockContent()
    }

}


