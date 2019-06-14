package com.artear.stevedore.stevedoreviews.repository.util

import com.artear.networking.url.BaseUrl


class EntireUrl(private val baseUrl: BaseUrl, private val path: String) {

    override fun toString(): String {
        return "$baseUrl$path"
    }
}