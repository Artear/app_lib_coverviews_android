/*
 * Copyright 2019 Artear S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.artear.stevedore.stevedoreviews.presentation

import com.artear.domain.coroutine.DataShaper
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItemDecoration
import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreviews.presentation.GridSpacePositionEnum.*
import com.artear.stevedore.stevedoreviews.presentation.model.EmptyItem
import com.artear.stevedore.stevedoreviews.repository.model.Container
import com.artear.stevedore.stevedoreviews.repository.model.Stevedore
import kotlin.math.roundToInt

class StevedoreDataShaper(private val stevedoreRegister: StevedoreRegister) :
        DataShaper<Stevedore, List<ArtearItem>> {

    var halfGap: Int = 0
    private var isRight = false
    private var isTop = true

    override suspend fun transform(input: Stevedore): List<ArtearItem> {

        val list: MutableList<ArtearItem> = mutableListOf()

        isRight = false
        isTop = true

        input.containers.forEach { container ->
            isTop = true
            halfGap = container.style.items.gap.roundToInt() / 2

            container.header?.let { header ->
                stevedoreRegister.headerShaper?.let { shaper ->
                    val artearItem = shaper.transform(header)
                    artearItem?.let { item ->

                        val rectHeader = container.header.style.margin.rect
                        val rectContainer = container.style.margin.rect

                        artearItem.artearItemDecoration = ArtearItemDecoration(
                                marginLeft = rectContainer.left,
                                marginTop = rectContainer.top,
                                marginRight = rectContainer.right,

                                paddingLeft = rectHeader.left,
                                paddingTop = rectHeader.top,
                                paddingRight = rectHeader.left,
                                paddingBottom = rectHeader.bottom
                        )

                        container.style.background?.color?.let { color ->
                            artearItem.artearItemDecoration.backgroundColor = color.light
                        }
                        list.add(item)
                    }
                }
            }



            container.items.forEachIndexed { index, box ->
                val shaperMap = stevedoreRegister.shaperMap
                if (shaperMap.containsKey(box.type)) {
                    val artearItem = shaperMap.getValue(box.type).transform(box)

                    artearItem?.let {
                        val gridSpacePosition = getItemPosition(box)
                        if (isTop && index > 0 && gridSpacePosition != RIGHT) {
                            isTop = false
                        }
                        val isBottom = isBottom(gridSpacePosition, index, container.items.size)

                        artearItem.artearItemDecoration = getArtearItemDecoration(
                                container,
                                gridSpacePosition,
                                isTop,
                                isBottom
                        )
                        list.add(it)

                        if (isBottom && gridSpacePosition == LEFT &&
                                index == container.items.size - 1) {
                            list.add(createEmptyItem(container))
                        }
                    }
                }
            }
        }
        return list
    }

    /**
    Item Creator used when collections have odd double items
     */
    private fun createEmptyItem(container: Container): ArtearItem {
        return ArtearItem(EmptyItem(), getArtearItemDecoration(
                container,
                RIGHT,
                isTop = false,
                isBottom = true
        ))
    }


    private fun isBottom(gridSpacePosition: GridSpacePositionEnum, index: Int, size: Int): Boolean {
        var result = false
        when (gridSpacePosition) {
            LEFT -> {
                if (index >= size - 2) {
                    result = true
                }
            }
            RIGHT, DOUBLE -> {
                if (index == size - 1) {
                    result = true
                }
            }
        }
        return result
    }

    private fun getItemPosition(box: Box): GridSpacePositionEnum {
        return when {
            box.style.weight == 1f -> {
                DOUBLE
            }
            else -> if (isRight) {
                isRight = false
                RIGHT
            } else {
                isRight = true
                LEFT
            }
        }
    }


    private fun getArtearItemDecoration(
            container: Container,
            position: GridSpacePositionEnum,
            isTop: Boolean,
            isBottom: Boolean
    ): ArtearItemDecoration {
        val marginLeft: Int
        val marginRight: Int
        var marginBottom = 0
        var marginTop = 0
        var paddingLeft = halfGap
        var paddingRight = halfGap

        var paddingTop = halfGap
        var paddingBottom = halfGap

        val rectContainer = container.style.margin.rect
        val rectItems = container.style.items.margin.rect


        when (position) {
            LEFT -> {
                marginLeft = rectContainer.left
                paddingLeft = rectItems.left

                marginRight = 0
                paddingRight = halfGap
            }
            RIGHT -> {
                marginLeft = 0
                paddingLeft = halfGap

                marginRight = rectContainer.right
                paddingRight = rectItems.right
            }
            DOUBLE -> {
                marginLeft = rectContainer.left
                paddingLeft = rectItems.left

                marginRight = rectContainer.right
                paddingRight = rectItems.right
            }
        }

        if (isTop) {
            paddingTop = rectItems.top
            if (container.header == null) {
                marginTop = rectContainer.top
            }
        }

        if (isBottom) {
            paddingBottom = rectItems.bottom
            marginBottom = rectContainer.bottom
        }


        val artearItemDecoration = ArtearItemDecoration(
                paddingLeft = paddingLeft,
                paddingTop = paddingTop,
                paddingRight = paddingRight,
                paddingBottom = paddingBottom,
                marginLeft = marginLeft,
                marginTop = marginTop,
                marginRight = marginRight,
                marginBottom = marginBottom
        )
        container.style.background?.color?.let {
            artearItemDecoration.backgroundColor = it.light
        }

        return artearItemDecoration
    }

}
