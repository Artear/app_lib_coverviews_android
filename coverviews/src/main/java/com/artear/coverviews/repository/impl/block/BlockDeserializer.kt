package com.artear.coverviews.repository.impl.block

import com.artear.coveritem.deserializer.DeserializerUtil
import com.artear.coveritem.deserializer.DeserializerUtil.Companion.getStyleFromJson
import com.artear.coveritem.deserializer.DeserializerUtil.Companion.getTypeFromJson
import com.artear.coveritem.deserializer.getModelObject
import com.artear.coveritem.model.BlockContent
import com.artear.coveritem.model.BlockType
import com.artear.coverviews.Manager
import com.artear.coverviews.repository.model.block.Block
import com.artear.coverviews.repository.model.block.BlockStyle
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class BlockDeserializer(private val manager: Manager) : JsonDeserializer<Block> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Block {
        val style = getStyleFromJson<BlockStyle>(context, json)
        val type = getTypeFromJson<BlockType>(context, json)
        val data = getBlockContentData(type, context, json)
        return Block(type, style, data)
    }

    private fun getBlockContentData(type: BlockType, context: JsonDeserializationContext,
                                    json: JsonElement): BlockContent {
        val blockContentClass = manager.getBlockContentClass(type)
        return json.getModelObject(DeserializerUtil.DATA, context, blockContentClass)
    }


}


