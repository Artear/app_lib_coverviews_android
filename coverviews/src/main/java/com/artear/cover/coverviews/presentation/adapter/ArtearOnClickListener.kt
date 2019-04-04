package com.artear.cover.coverviews.presentation.adapter

import com.artear.cover.coveritem.model.link.Link


interface ArtearOnClickListener {

    fun onArticleClick(link: Link)

    fun onCategoryClick(link: Link)

    fun onTagClick(link: Link)
}
