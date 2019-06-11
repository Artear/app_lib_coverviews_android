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