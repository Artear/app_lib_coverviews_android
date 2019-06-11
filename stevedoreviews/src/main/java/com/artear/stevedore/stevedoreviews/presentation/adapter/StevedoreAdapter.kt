package com.artear.stevedore.stevedoreviews.presentation.adapter

import com.artear.stevedore.stevedoreitems.presentation.adapter.DefaultItemAdapter
import com.artear.stevedore.stevedoreitems.presentation.adapter.SpanContentAdapter
import com.artear.stevedore.stevedoreitems.presentation.contract.ItemAdapter
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem


class StevedoreAdapter(adapters: List<ItemAdapter<*>>) : SpanContentAdapter(2) {

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
