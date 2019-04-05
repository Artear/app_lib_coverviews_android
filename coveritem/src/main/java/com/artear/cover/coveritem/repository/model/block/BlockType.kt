package com.artear.cover.coveritem.repository.model.block

import com.google.gson.annotations.SerializedName

enum class BlockType(val description: String){
    @SerializedName("article")
    ARTICLE("article"),
    @SerializedName("category")
    CATEGORY("category"),
    @SerializedName("media")
    MEDIA("media"),
    @SerializedName("dfp")
    DFP("dfp");
}