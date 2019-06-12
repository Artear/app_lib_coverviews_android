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
import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxType
import com.artear.stevedore.stevedoreviews.repository.model.Stevedore

class StevedoreDataShaper(private val shaperMap: Map<BoxType, DataShaper<Box, ArtearItem>>) :
        DataShaper<Stevedore, List<ArtearItem>> {

    override suspend fun transform(input: Stevedore): List<ArtearItem> {

        val list: MutableList<ArtearItem> = mutableListOf()

        input.containers.forEach { container ->
            container.items.forEach {
                if (shaperMap.containsKey(it.type)) {
                    val artearItem = shaperMap.getValue(it.type).transform(it)
                    artearItem?.let {
                        list.add(it)
                    }
                }
            }
        }

        return list
    }
}