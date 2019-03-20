package com.artear.articleitem

import com.artear.coveritem.model.BlockContent
import com.artear.coveritem.model.link.Link
import com.artear.coveritem.model.media.Media
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(BlockContentArticleDeserializer::class)
data class BlockContentArticle(
        val id: Int, val title: String, val description: String,
        val link: Link?, val media: Media?
) : BlockContent()