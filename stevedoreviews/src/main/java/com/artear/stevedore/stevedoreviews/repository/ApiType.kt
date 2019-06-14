package com.artear.stevedore.stevedoreviews.repository


interface ApiType {
    fun <T> getUrl(param: T?): String
}