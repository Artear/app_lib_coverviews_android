package com.artear.stevedoreviewsexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artear.stevedore.stevedoreitems.presentation.contract.ArtearViewHolder
import com.artear.stevedore.stevedoreitems.presentation.contract.ItemAdapter
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearSection

class LoadingItemAdapter(private val listener: PagingErrorView.OnReloadClickListener?) :
        ItemAdapter<LoadingData> {

    var viewHolder: RecyclerView.ViewHolder? = null
    var idLayout: Int? = null

    override fun isForViewType(item: ArtearItem): Boolean {
        return item.model is LoadingData
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(idLayout ?: R.layout.loading_paging, parent, false)
        return viewHolder ?: LoadingViewHolder(view, listener)
    }

    override fun onBindViewHolderBase(holder: ArtearViewHolder<LoadingData>, model: LoadingData,
                                      artearSection: ArtearSection) {
        holder.bind(model, artearSection)
    }
}