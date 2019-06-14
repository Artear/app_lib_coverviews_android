package com.artear.stevedore.stevedoreviews.repository


class ApiAction(private val apiType: ApiType, override var param: Any? = null) : Action {
    override fun get() = apiType.getUrl(param)
}