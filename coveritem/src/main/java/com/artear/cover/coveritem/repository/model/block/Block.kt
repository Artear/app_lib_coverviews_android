package com.artear.cover.coveritem.repository.model.block

import com.artear.cover.coveritem.repository.model.BlockContent

data class Block(var type: BlockType, val style: BlockStyle, val data: BlockContent)