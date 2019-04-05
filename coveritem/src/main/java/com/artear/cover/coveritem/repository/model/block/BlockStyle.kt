package com.artear.cover.coveritem.repository.model.block

import com.google.gson.annotations.SerializedName


class BlockStyle(
        @SerializedName("position")
        var position: Int? = 0,
        @SerializedName("underline")
        val underline: Boolean? = false,
        @SerializedName("underline_color")
        val underlineColor: String? = null,
        @SerializedName("description_hidden")
        val descriptionHidden: Boolean? = true,
        @SerializedName("weight")
        val weight: Float? = 1f,
        @SerializedName("background_color")
        val backgroundColor: String? = null,
        @SerializedName("title_text_color")
        val titleTextColor: String? = null,
        @SerializedName("description_text_color")
        val descriptionTextColor: String? = null,
        @SerializedName("title_number_of_lines")
        var titleNumberLines: Int? = 0,
        @SerializedName("description_number_of_lines")
        var descriptionNumberLines: Int? = 0
)