package com.artear.cover.coverviews.repository.impl.block

import com.artear.cover.coveritem.DeserializerUtil
import com.artear.cover.coveritem.DeserializerUtil.Companion.getStyleFromJson
import com.artear.cover.coveritem.DeserializerUtil.Companion.getTypeFromJson
import com.artear.cover.coveritem.getModelObject
import com.artear.cover.coveritem.model.BlockContent
import com.artear.cover.coveritem.model.BlockType
import com.artear.cover.coverviews.Manager
import com.artear.cover.coverviews.repository.model.block.Block
import com.artear.cover.coverviews.repository.model.block.BlockStyle
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


