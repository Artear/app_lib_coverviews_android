package com.artear.cover.coveritem.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artear.cover.coveritem.presentation.model.ArtearItem


class ItemAdapterManager {

    companion object {
        const val DEFAULT_ADAPTER_VIEW_TYPE = -1
    }

    private val listItemAdapter: MutableList<ItemAdapter<*>> = ArrayList()
    private var defaultItemAdapter: ItemAdapter<*>? = null

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == DEFAULT_ADAPTER_VIEW_TYPE) {
            defaultItemAdapter?.let { return it.onCreateViewHolder(parent) }
            throw IllegalStateException("You must set defaultItemAdapter to create a view holder")
        }

        val itemAdapter = listItemAdapter[viewType]
        return itemAdapter.onCreateViewHolder(parent)
    }

    fun onBindViewHolder(list: List<ArtearItem>, holder: RecyclerView.ViewHolder, position: Int) {

        val viewType = getItemViewType(list, position)
        val artearItem = list[position]

        if (viewType == DEFAULT_ADAPTER_VIEW_TYPE) {
            defaultItemAdapter?.let {
                it.onBindViewHolder(holder, artearItem)
                return
            }
            throw IllegalStateException("You must to set defaultItemAdapter to bind it")
        }

        val itemAdapter = listItemAdapter[viewType]
        itemAdapter.onBindViewHolder(holder, artearItem)
    }

    fun getItemViewType(list: List<ArtearItem>, position: Int): Int {
        listItemAdapter.forEachIndexed { index, itemAdapter ->
            if (itemAdapter.isForViewType(list[position])) return index
        }

        defaultItemAdapter?.let { return DEFAULT_ADAPTER_VIEW_TYPE }

        throw IllegalStateException("There are not any adapter to manage this type of Item")
    }

    fun addAdapter(itemAdapter: ItemAdapter<*>) {
        listItemAdapter.add(itemAdapter)
    }

    fun setDefaultAdapter(defaultItemAdapter: DefaultItemAdapter) {
        this.defaultItemAdapter = defaultItemAdapter
    }

}
