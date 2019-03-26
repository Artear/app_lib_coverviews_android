package com.artear.cover.coverviews

import com.artear.cover.coverviews.model.EmptyItem
import com.artear.cover.coverviews.repository.model.ArtearItem
import com.artear.cover.coverviews.repository.model.ArtearObject
import com.artear.cover.coverviews.repository.model.block.Block
import com.artear.cover.coverviews.repository.model.block.BlockStyle
import com.artear.cover.coverviews.repository.model.container.ContainerHeader
import com.artear.cover.coverviews.repository.model.container.ContainerStyle
import com.artear.cover.coverviews.repository.model.container.Stevedore

object DataShapeHelper {

    const val headerIndex = 0

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
                    getArtearSection(headerIndex, style)
            )
        }
        return result
    }


    fun containerItemShaper(stevedore: Stevedore): MutableList<ArtearItem> {
        val list: MutableList<ArtearItem> = ArrayList()
        var isDoubleLeft = true

        stevedore.containers.mapIndexed { index, container ->

            getHeader(container.header, container.style)?.let {
                list.add(it)
            }

            var itemPosition = 0
            val sectionStyle = getArtearSection(index + 1, container.style)
            var nullFiller: ArtearItem? = null
            while (itemPosition < container.items.size) {

                var artearItem: ArtearItem?
                val item = container.items[itemPosition]

                artearItem = makeArtearItem(
                        item,
                        sectionStyle
                )


                if (item.style.weight == 0.5f) {
                    item.style.position = when {
                        isDoubleLeft -> 1
                        else -> 2
                    }

                    if (isDoubleLeft) {
                        if ((itemPosition + 1) >= container.items.size) {
                            nullFiller = makeArtearItem(null, sectionStyle)
                        } else {
                            if (container.items[itemPosition + 1].style.weight == 1f) {
                                nullFiller = makeArtearItem(null, sectionStyle)
                                isDoubleLeft = true
                            }
                        }
                    }
                    isDoubleLeft = !isDoubleLeft
                }

                artearItem?.let {
                    list.add(it)
                }
                nullFiller?.let {
                    list.add(it)
                }
                nullFiller = null
                itemPosition++
            }
        }
        return list
    }

    private fun makeArtearItem(item: Block?, sectionStyle: ArtearSection): ArtearItem? {
        var result: ArtearItem? = null
        if (item == null) {
            result = makeEmptyItem(sectionStyle)
        } else {
            // TODO: result = MAGICItemCreator() ("ContentDataManager ? MANAGE THINGS")
        }
        return result


    }

    private fun makeEmptyItem(sectionStyle: ArtearSection): ArtearItem? {
        return ArtearItem(
                EmptyItem(),
                BlockStyle(weight = 0.5f),
                sectionStyle
        )
    }

    private fun getArtearSection(
            index: Int,
            style: ContainerStyle?
    ) = when {
        style != null -> ArtearSection(
                style.backgroundColor,
                style.textColor,
                style.margin,
                index
        )
        else -> ArtearSection()
    }
}