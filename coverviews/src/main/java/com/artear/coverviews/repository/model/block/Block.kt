package com.artear.coverviews.repository.model.block


import com.artear.coveritem.model.BlockContent
import com.artear.coveritem.model.BlockType


data class Block(var type: BlockType, val style: BlockStyle, val data: BlockContent)