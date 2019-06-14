package com.artear.stevedore.stevedoreviews.repository


interface ApiType {
    fun getUrl(param: Any?): String
}