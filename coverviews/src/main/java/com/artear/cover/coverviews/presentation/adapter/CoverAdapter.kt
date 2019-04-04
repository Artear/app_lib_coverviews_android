package com.artear.cover.coverviews.presentation.adapter

import com.artear.cover.coveritem.presentation.model.ArtearItem


class CoverAdapter(onItemClickHandler: ArtearOnClickListener?) : ContentAdapter() {

    init {
        //Agrego solo los que se dibujar
        itemAdapterManager.setDefaultAdapter(DefaultItemAdapter())
        itemAdapterManager.addAdapter(TitleSectionItemAdapter())
        itemAdapterManager.addAdapter(ContentItemAdapter(onItemClickHandler))
        itemAdapterManager.addAdapter(MediaItemAdapter())
        itemAdapterManager.addAdapter(DfpAdapter())
        itemAdapterManager.addAdapter(EmptyItemAdapter())
    }

    fun setData(data: List<ArtearItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}
