package com.artear.cover.coveritem.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.presentation.model.ArtearObject
import com.artear.cover.coveritem.presentation.model.ArtearSection


interface ItemAdapter<T : ArtearObject<*>> {

    fun isForViewType(item: ArtearItem): Boolean

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    @Suppress("UNCHECKED_CAST")
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, artearItem: ArtearItem) {
        onBindViewHolderBase(holder as ArtearViewHolder<T>, artearItem.model as T, artearItem.section)
    }

    fun onBindViewHolderBase(holder: ArtearViewHolder<T>, model: T, artearSection: ArtearSection)
}