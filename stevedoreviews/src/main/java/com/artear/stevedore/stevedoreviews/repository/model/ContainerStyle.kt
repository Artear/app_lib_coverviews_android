package com.artear.stevedore.stevedoreviews.repository.model

import com.artear.stevedore.stevedoreitems.repository.model.style.StyleBackground
import com.artear.stevedore.stevedoreitems.repository.model.style.StyleMargin


data class ContainerStyle(val type: ContainerType, val background: StyleBackground?,
                          val margin: StyleMargin, val items: StyleBoxItems)
