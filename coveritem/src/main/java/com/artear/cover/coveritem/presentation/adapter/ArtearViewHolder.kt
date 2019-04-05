package com.artear.cover.coveritem.presentation.adapter

import com.artear.cover.coveritem.presentation.model.ArtearObject
import com.artear.cover.coveritem.presentation.model.ArtearSection


interface ArtearViewHolder<T : ArtearObject<*>> {
    var listener: ArtearOnClickListener?
    fun bind(model: T, artearSection: ArtearSection)
}