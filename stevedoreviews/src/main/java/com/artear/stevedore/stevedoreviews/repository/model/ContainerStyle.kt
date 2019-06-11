package com.artear.stevedore.stevedoreviews.repository.model

import com.google.gson.annotations.SerializedName

data class ContainerStyle(
        val type: String?,
        @SerializedName("background_color")
        val backgroundColor: String?,
        @SerializedName("text_color")
        val textColor: String?,
        @SerializedName("title_text_color")
        val titleTextColor: String?,
        val margin: Boolean?
)