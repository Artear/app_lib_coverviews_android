package com.artear.stevedore.stevedoreviews.repository


interface Action {
    var param: Any?
    fun get() : String
}