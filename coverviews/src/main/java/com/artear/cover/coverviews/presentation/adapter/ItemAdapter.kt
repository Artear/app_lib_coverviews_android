package com.artear.cover.coverviews.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artear.cover.coverviews.presentation.model.ArtearItem
import com.artear.cover.coverviews.presentation.model.ArtearObject
import com.artear.cover.coverviews.presentation.model.ArtearSection
import com.artear.cover.coverviews.repository.model.block.BlockStyle


interface ItemAdapter<T : ArtearObject> {

    fun isForViewType(item: ArtearItem): Boolean

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    @Suppress("UNCHECKED_CAST")
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, artearItem: ArtearItem) {
        onBindViewHolderBase(holder as ArtearViewHolder<T>, artearItem.model as T,
                artearItem.style, artearItem.section)
    }

    fun onBindViewHolderBase(holder: ArtearViewHolder<T>, model: T,
                             blockStyle: BlockStyle,
                             artearSection: ArtearSection)
}