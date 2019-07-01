package com.artear.stevedore.stevedoreviews.presentation


data class PageParam<out Param>(val param: Param, val page: String, val size: Int? = null)