package com.artear.coverviews

import com.artear.coverviews.repository.model.ArtearItem
import com.artear.coverviews.repository.model.ArtearObject
import com.artear.coverviews.repository.model.block.BlockStyle
import com.artear.coverviews.repository.model.container.ContainerHeader
import com.artear.coverviews.repository.model.container.ContainerStyle
import com.artear.coverviews.repository.model.container.Stevedore

object DataShapeHelper {


    fun getHeader(header: ContainerHeader?, style: ContainerStyle?): ArtearItem? {

        var result: ArtearItem? = null
        header?.title?.let {
            var blockStyle = BlockStyle()
            header.style?.let {
                blockStyle = BlockStyle(
                        backgroundColor = header.style.backgroundColor,
                        titleTextColor = header.style.textColor
                )
            }
            result = ArtearItem(
                    ArtearObject(), // TODO: TitleSectionData(it),
                    blockStyle,
                    getArtearSection(style)
            )

        }
        return result
    }


    fun containerItemShaper(stevedore: Stevedore): MutableList<ArtearItem> {
        val list: MutableList<ArtearItem> = ArrayList()
        stevedore.containers.map { container ->

            var itemsCount = container.items.size

            getHeader(container.header, container.style)?.let {
                list.add(it)
            }
            var itemPosition = 0
            val sectionStyle = getArtearSection(container.style)
//            while (itemPosition < container.items.size) {
//
//                var artearItem: ArtearItem?
//                val item = container.items[itemPosition]
//
//
//                if (item.style.weight != 1f) {
//                    if ((itemPosition + 1) < container.items.size) {
//                        val item2 = container.items[itemPosition + 1]
//                        if (item2.style.weight != 1f) {
//                            artearItem =
//                                    makeArtearDoubleItem(item, item2, sectionStyle)
//                            itemPosition++
//                        } else {
//                            artearItem =
//                                    makeArtearDoubleItem(item, null, sectionStyle)
//                        }
//                    } else {
//                        artearItem =
//                                makeArtearDoubleItem(item, null, sectionStyle)
//                    }
//                } else {
//                    artearItem = makeArtearItem(
//                            item,
//                            sectionStyle
//                    )
//                }
//                artearItem?.let {
//                    list.add(it)
//                }
//                itemPosition++
//            }

        }
        return list
    }

    private fun getArtearSection(
            style: ContainerStyle?
    ) = when {
        style != null -> ArtearSection(
                style.backgroundColor,
                style.textColor,
                style.margin
        )
        else -> ArtearSection()
    }
}