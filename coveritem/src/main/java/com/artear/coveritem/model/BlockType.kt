package com.artear.coveritem.model

import com.google.gson.annotations.SerializedName

//TODO: Ver forma dinamica de tipos
enum class BlockType(val description: String) {
    @SerializedName("article")
    ARTICLE("article"),
    @SerializedName("category")
    CATEGORY("category"),
    @SerializedName("media")
    MEDIA("media"),
    @SerializedName("dfp")
    DFP("dfp");

    companion object {

        fun fromString(text: String?): BlockType {

            if (text != null) {
                for (block in BlockType.values()) {
                    if (block.description == text) {
                        return block
                    }
                }
            }

            throw IllegalArgumentException("Can't convert to BlockType, no string $text found")
        }

        fun valueOf(value: Int): BlockType {
            return BlockType.values()[value]
        }

    }

}