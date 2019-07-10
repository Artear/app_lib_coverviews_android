package com.artear.stevedore.stevedoreviews.presentation.model

import com.artear.stevedore.stevedoreitems.presentation.model.ArtearObject
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxStyle

class EmptyItem(val weight: Float = 0.5f) : ArtearObject<BoxStyle>()