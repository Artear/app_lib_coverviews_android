package com.artear.stevedore.stevedoreviews.repository


class ApiAction<T>(private val apiType: ApiType, param: T? = null) : Action(param) {

    override fun toString(): String {
        return apiType.getUrl(param)
    }
}