package com.artear.coverviews.repository.model

import com.artear.coverviews.ArtearSection
import com.artear.coverviews.repository.model.block.BlockStyle

class ArtearItem(val model: ArtearObject, val style: BlockStyle, val section: ArtearSection) : ArtearObject()
