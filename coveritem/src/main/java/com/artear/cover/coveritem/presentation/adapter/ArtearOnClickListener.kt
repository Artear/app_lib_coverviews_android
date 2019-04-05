package com.artear.cover.coveritem.presentation.adapter

import com.artear.cover.coveritem.repository.model.link.Link


interface ArtearOnClickListener {

    fun onArticleClick(link: Link)

    fun onCategoryClick(link: Link)

    fun onTagClick(link: Link)
}
