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
import com.artear.stevedore.stevedoreviews.repository.model.Container
import com.artear.stevedore.stevedoreviews.repository.model.ContainerStyle
import com.artear.stevedore.stevedoreviews.repository.model.Stevedore
import kotlin.math.roundToInt

class StevedoreDataShaper(private val stevedoreRegister: StevedoreRegister) :
        DataShaper<Stevedore, List<ArtearItem>> {

    override suspend fun transform(input: Stevedore): List<ArtearItem> {

        val list: MutableList<ArtearItem> = mutableListOf()

        input.containers.forEach { container ->

            container.header?.let { header ->
                stevedoreRegister.headerShaper?.let { shaper ->
                    val artearItem = shaper.transform(header)
                    decorateArtearItem(artearItem, container, list)
                }
            }

            container.items.forEach { box ->
                val shaperMap = stevedoreRegister.shaperMap
                if (shaperMap.containsKey(box.type)) {
                    val artearItem = shaperMap.getValue(box.type).transform(box)


                    decorateArtearItem(artearItem, container, list)
                }
            }
        }

        return list
    }

    private fun decorateArtearItem(artearItem: ArtearItem?, container: Container, list: MutableList<ArtearItem>) {
        artearItem?.let {
            artearItem.artearItemDecoration = getArtearItemDecoration(container.style)
            list.add(it)
        }
    }

    private fun getArtearItemDecoration(containerStyle: ContainerStyle): ArtearItemDecoration {
        val artearItemDecoration = ArtearItemDecoration()

        val halfGap = containerStyle.items.gap.roundToInt() / 2

        var marginLeft = 0
        var marginRight = 0
        var marginTop = halfGap
        var marginBottom = halfGap

        //TODO: check backgrounds ascending
        containerStyle.background?.color?.let {
            artearItemDecoration.backgroundColor = it.light
        }


        if (isFullWidth()) {
            marginLeft = containerStyle.margin.rect.left + containerStyle.items.margin.rect.left
            marginRight = containerStyle.margin.rect.right + containerStyle.items.margin.rect.right
        } else {
            if (isLeftItem()) {
                marginLeft = containerStyle.margin.rect.left + containerStyle.items.margin.rect.left
                marginRight = containerStyle.margin.rect.right + (halfGap)
            } else {
                marginRight = containerStyle.margin.rect.right + containerStyle.items.margin.rect.right
                marginLeft = containerStyle.margin.rect.left + (halfGap)
            }
        }

        if (isTop()) {
            marginTop = containerStyle.margin.rect.top + containerStyle.items.margin.rect.top
        }

        if (isBottom()) {
            marginBottom = containerStyle.margin.rect.bottom + containerStyle.items.margin.rect.bottom
        }

        artearItemDecoration.marginBottom = marginBottom
        artearItemDecoration.marginLeft = marginLeft
        artearItemDecoration.marginRight = marginRight
        artearItemDecoration.marginTop = marginTop


        return artearItemDecoration
    }

    private fun isBottom(): Boolean {
        return true
    }

    private fun isLeftItem(): Boolean {
        return true
    }

    private fun isTop(): Boolean {
        return true
    }

    private fun isFullWidth(): Boolean {
        return false
    }
}
