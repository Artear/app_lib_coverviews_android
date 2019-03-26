package com.artear.cover.articleitem

import com.artear.cover.coveritem.model.BlockContent
import com.artear.cover.coveritem.model.link.Link
import com.artear.cover.coveritem.model.media.Media
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(BlockContentArticleDeserializer::class)
data class BlockContentArticle(
        val id: Int, val title: String, val description: String,
        val link: Link?, val media: Media?
) : BlockContent()