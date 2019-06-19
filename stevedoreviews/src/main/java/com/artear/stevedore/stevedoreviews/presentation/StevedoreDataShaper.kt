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
import com.artear.stevedore.stevedoreviews.repository.model.Stevedore

class StevedoreDataShaper(private val stevedoreRegister: StevedoreRegister) :
        DataShaper<Stevedore, List<ArtearItem>> {

    override suspend fun transform(input: Stevedore): List<ArtearItem> {

        val list: MutableList<ArtearItem> = mutableListOf()

        input.containers.forEach { container ->

            container.header?.let { header ->
                stevedoreRegister.headerShaper?.let { shaper ->
                    val artearItem = shaper.transform(header)
                    artearItem?.let {
                        list.add(it)
                    }
                }
            }

            container.items.forEach { box ->
                val shaperMap = stevedoreRegister.shaperMap
                if (shaperMap.containsKey(box.type)) {
                    val artearItem = shaperMap.getValue(box.type).transform(box)
                    artearItem?.let {
                        list.add(it)
                    }
                }
            }
        }

        return list
    }
}