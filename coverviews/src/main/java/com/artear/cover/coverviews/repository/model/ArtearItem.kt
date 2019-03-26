package com.artear.cover.coverviews.repository.model

import com.artear.cover.coverviews.ArtearSection
import com.artear.cover.coverviews.repository.model.block.BlockStyle

class ArtearItem(val model: ArtearObject, val style: BlockStyle, val section: ArtearSection) : ArtearObject()
