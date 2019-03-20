package com.artear.coverviews

import com.artear.coveritem.model.BlockContent
import com.artear.coveritem.model.BlockType
import com.artear.coverviews.repository.model.ArtearObject
import com.artear.domain.coroutine.DataShaper
import com.google.gson.JsonDeserializer


class Manager {
    fun <T : BlockContent, H : ArtearObject>
            registerTypeDeserializer(block: BlockType,
                                     deserializer: JsonDeserializer<T>,
                                     shaper: DataShaper<T, H>) {

    }
}