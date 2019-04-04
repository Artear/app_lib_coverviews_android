package com.artear.cover.coverviews.presentation.adapter

import com.artear.cover.coverviews.presentation.model.ArtearObject
import com.artear.cover.coverviews.presentation.model.ArtearSection
import com.artear.cover.coverviews.repository.model.block.BlockStyle


interface ArtearViewHolder<T : ArtearObject> {
    var listener: ArtearOnClickListener?
    fun bind(model: T, blockStyle: BlockStyle, artearSection: ArtearSection)
}