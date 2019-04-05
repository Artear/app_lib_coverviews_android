package com.artear.cover.coveritem.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artear.cover.coveritem.presentation.model.ArtearItem


abstract class ContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    protected val itemAdapterManager: ItemAdapterManager = ItemAdapterManager()
    val list: MutableList<ArtearItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return itemAdapterManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemAdapterManager.onBindViewHolder(list, holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return itemAdapterManager.getItemViewType(list, position)
    }

    open fun clear() {
    }

    open fun onPause() {
    }

    open fun hasHeader(): Boolean {
        return false
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun isEmpty(): Boolean {
        return itemCount == 0 || (hasHeader() && itemCount == 1)
    }
}