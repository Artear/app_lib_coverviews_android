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
import com.artear.stevedore.headeritem.repository.ContainerHeader
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreitems.repository.model.box.Box
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxType


class StevedoreRegister private constructor() {

    val shaperMap = mutableMapOf<BoxType, DataShaper<Box, ArtearItem>>()
    var headerShaper: DataShaper<ContainerHeader, ArtearItem>? = null

    class Builder {

        private val stevedoreRegister = StevedoreRegister()

        fun add(boxType: BoxType, shaper: DataShaper<Box, ArtearItem>) = apply {
            stevedoreRegister.shaperMap[boxType] = shaper
        }

        fun addHeader(headerShaper: DataShaper<ContainerHeader, ArtearItem>) {
            stevedoreRegister.headerShaper = headerShaper
        }

        fun build(): StevedoreRegister {
            if (stevedoreRegister.shaperMap.isEmpty())
                throw IllegalStateException("The register must have almost one element")

            return stevedoreRegister
        }

    }
}