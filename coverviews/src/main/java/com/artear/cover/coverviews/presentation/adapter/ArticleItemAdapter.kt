package com.artear.cover.coverviews.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artear.cover.articleitem.ContentData
import com.artear.cover.articleitem.ContentViewHolder
import com.artear.cover.articleitem.R
import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.presentation.model.ArtearSection
import com.artear.cover.coverviews.repository.model.block.BlockStyle


class ArticleItemAdapter(val listener: ArtearOnClickListener?) : ItemAdapter<ContentData> {

    override fun isForViewType(item: ArtearItem): Boolean {
        return item.model is ContentData
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_view_holder, parent, false)
        return ContentViewHolder(view, listener)
    }

    override fun onBindViewHolderBase(holder: ArtearViewHolder<ContentData>, model: ContentData,
                                      blockStyle: BlockStyle, artearSection: ArtearSection) {
        holder.bind(model, blockStyle, artearSection)
    }
}