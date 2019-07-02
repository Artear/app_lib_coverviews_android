package com.artear.stevedore.stevedoreviews.presentation


data class PageParam<out Param>(val param: Param? = null,
                                val page: String? = null,
                                val size: Int? = null)