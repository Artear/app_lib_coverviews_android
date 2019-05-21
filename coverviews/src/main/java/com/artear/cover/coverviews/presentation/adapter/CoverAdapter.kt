package com.artear.cover.coverviews.presentation.adapter

import com.artear.cover.coveritem.presentation.adapter.ContentAdapter
import com.artear.cover.coveritem.presentation.adapter.DefaultItemAdapter
import com.artear.cover.coveritem.presentation.contract.ItemAdapter
import com.artear.cover.coveritem.presentation.model.ArtearItem


class CoverAdapter(adapters: List<ItemAdapter<*>>) : ContentAdapter() {

    init {
        itemAdapterManager.setDefaultAdapter(DefaultItemAdapter())
        adapters.forEach { itemAdapterManager.addAdapter(it) }
    }

    fun setData(data: List<ArtearItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}
