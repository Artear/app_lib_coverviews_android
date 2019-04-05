package com.artear.cover.articleitem

import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.repository.model.block.Block
import com.artear.domain.coroutine.DataShaper


class ArticleShaper : DataShaper<Block, ArtearItem> {

    override suspend fun transform(input: Block): ArtearItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}