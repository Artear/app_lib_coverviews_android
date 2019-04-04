package com.artear.cover.articleitem

//
//class ArticleItemAdapter(val listener: ArtearOnClickListener?) : ItemAdapter<ContentData> {
//
//    override fun isForViewType(item: ArtearItem): Boolean {
//        return item.model is ContentData
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.article_view_holder, parent, false)
//        return ContentViewHolder(view, listener)
//    }
//
//    override fun onBindViewHolderBase(holder: ArtearViewHolder<ContentData>, model: ContentData,
//                                      blockStyle: BlockStyle, artearSection: ArtearSection) {
//        holder.bind(model, blockStyle, artearSection)
//    }
//}