package com.artear.cover.coverviews.presentation.adapter

import com.artear.cover.coveritem.presentation.adapter.ArtearOnClickListener
import com.artear.cover.coveritem.presentation.adapter.ContentAdapter
import com.artear.cover.coveritem.presentation.adapter.DefaultItemAdapter
import com.artear.cover.coveritem.presentation.adapter.ItemAdapter
import com.artear.cover.coveritem.presentation.model.ArtearItem


class CoverAdapter(adapters: List<ItemAdapter<*>>, onItemClickHandler: ArtearOnClickListener?) : ContentAdapter() {

    init {
        //Agrego solo los que se dibujar
        itemAdapterManager.setDefaultAdapter(DefaultItemAdapter())
        adapters.forEach { itemAdapterManager.addAdapter(it) }
//        itemAdapterManager.addAdapter(TitleSectionItemAdapter())
//        itemAdapterManager.addAdapter(ContentItemAdapter(onItemClickHandler))
//        itemAdapterManager.addAdapter(MediaItemAdapter())
//        itemAdapterManager.addAdapter(DfpAdapter())
//        itemAdapterManager.addAdapter(EmptyItemAdapter())
    }

    fun setData(data: List<ArtearItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}
