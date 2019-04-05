package com.artear.cover.articleitem

import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.presentation.model.ArtearSection
import com.artear.cover.coveritem.repository.model.block.Block
import com.artear.cover.coveritem.repository.model.media.MediaType
import com.artear.domain.coroutine.DataShaper


class ArticleShaper : DataShaper<Block, ArtearItem> {

    override suspend fun transform(input: Block): ArtearItem {

        val blockContentArticle = (input.data as BlockContentArticle)

        val data = ArticleData("image",
                blockContentArticle.title,
                blockContentArticle.description,
                blockContentArticle.link,
                blockContentArticle.media?.type == MediaType.YOUTUBE,
                ArticleStyle("#456765")
        )

        return ArtearItem(data, ArtearSection())
    }


}