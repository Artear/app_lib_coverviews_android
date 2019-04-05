package com.artear.cover.articleitem

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.artear.cover.coveritem.presentation.adapter.ArtearOnClickListener
import com.artear.cover.coveritem.presentation.adapter.ArtearViewHolder
import com.artear.cover.coveritem.presentation.model.ArtearSection
import kotlinx.android.synthetic.main.article_view_holder.view.*


class ContentViewHolder(itemView: View, override var listener: ArtearOnClickListener? = null) :
        RecyclerView.ViewHolder(itemView), ArtearViewHolder<ContentData<*>> {

    override fun bind(model: ContentData<*>, artearSection: ArtearSection) {

        model.style

        itemView.apply {
           contentTitle.text = model.title
        }

        itemView.setOnClickListener {
            listener?.run {
                model.link?.let { link ->
                    onArticleClick(link)
                }
            }
        }

    }

}