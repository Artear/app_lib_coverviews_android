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

        input.containers.forEach { container ->
            isTop = true
            halfGap = container.style.items.gap.roundToInt() / 2

            container.header?.let { header ->
                stevedoreRegister.headerShaper?.let { shaper ->
                    val artearItem = shaper.transform(header)
                    artearItem?.let { item ->

                        val rectHeader = container.header.style.margin.rect
                        val rectContainer = container.style.margin.rect

                        val marginLeft = rectHeader.left + rectContainer.left
                        val marginRight = rectHeader.right + rectContainer.right
                        val marginTop = rectHeader.top + rectContainer.top
                        val marginBottom = rectHeader.bottom + rectContainer.bottom

                        artearItem.artearItemDecoration = ArtearItemDecoration(
                                marginTop = marginTop,
                                marginLeft = marginLeft,
                                marginBottom = marginBottom,
                                marginRight = marginRight
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

    private fun createEmptyItem(container: Container): ArtearItem {
        return ArtearItem(EmptyItem(), getArtearItemDecoration(
                container,
                RIGHT,
                isTop,
                true
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
                isTop = false
                DOUBLE
            }
            else -> if (isRight) {
                isTop = false
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
        val artearItemDecoration = ArtearItemDecoration()

        val marginLeft: Int
        val marginRight: Int
        var marginTop = halfGap
        var marginBottom = halfGap

        val rectContainer = container.style.margin.rect
        val rectItems = container.style.items.margin.rect

        container.style.background?.color?.let {
            artearItemDecoration.backgroundColor = it.light
        }

        when (position) {
            LEFT -> {
                marginLeft = rectContainer.left + rectItems.left
                marginRight = rectContainer.right + (halfGap)
            }
            RIGHT -> {
                marginRight = rectContainer.right + rectItems.right
                marginLeft = rectContainer.left + (halfGap)
            }
            DOUBLE -> {
                marginLeft = rectContainer.left + rectItems.left
                marginRight = rectContainer.right + rectItems.right
            }
        }

        if (isTop) {
            marginTop = rectItems.top
            if (container.header == null) {
                marginTop += rectContainer.top
            }
        }

        if (isBottom) {
            marginBottom = rectContainer.bottom + rectItems.bottom
        }

        artearItemDecoration.marginBottom = marginBottom
        artearItemDecoration.marginLeft = marginLeft
        artearItemDecoration.marginRight = marginRight
        artearItemDecoration.marginTop = marginTop


        return artearItemDecoration
    }

}
