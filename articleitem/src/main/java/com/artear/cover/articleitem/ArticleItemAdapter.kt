package com.artear.cover.articleitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artear.cover.coveritem.presentation.adapter.ArtearOnClickListener
import com.artear.cover.coveritem.presentation.adapter.ArtearViewHolder
import com.artear.cover.coveritem.presentation.adapter.ItemAdapter
import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.presentation.model.ArtearSection


class ArticleItemAdapter(private val listener: ArtearOnClickListener?) : ItemAdapter<ArticleData<*>> {

    override fun isForViewType(item: ArtearItem): Boolean {
        return item.model is ArticleData
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_view_holder, parent, false)
        return ContentViewHolder(view, listener)
    }

    override fun onBindViewHolderBase(holder: ArtearViewHolder<ArticleData<*>>,
                                      model: ArticleData<*>, artearSection: ArtearSection) {
        holder.bind(model, artearSection)
    }
}