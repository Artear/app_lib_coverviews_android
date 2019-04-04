package com.artear.cover.articleitem

import com.artear.cover.coveritem.presentation.model.ArtearObject
import com.artear.cover.coveritem.repository.model.link.Link


data class ContentData(
        val imageUrl: String,
        val title: String,
        val description: String?,
        val link: Link?,
        val isVideoContent: Boolean) : ArtearObject()
