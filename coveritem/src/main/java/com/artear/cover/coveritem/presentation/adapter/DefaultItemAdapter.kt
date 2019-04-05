package com.artear.cover.coveritem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artear.cover.coveritem.R
import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.presentation.model.ArtearObject
import com.artear.cover.coveritem.presentation.model.ArtearSection


class DefaultItemAdapter : ItemAdapter<ArtearObject<*>> {

    override fun isForViewType(item: ArtearItem): Boolean {
        return false
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.empty_item_layout, parent, false)
        return DefaultViewHolder(view)
    }

    override fun onBindViewHolderBase(holder: ArtearViewHolder<ArtearObject<*>>,
                                      model: ArtearObject<*>, artearSection: ArtearSection) {
        holder.bind(model, artearSection)
    }
}