package com.artear.stevedore.stevedoreviews.repository.contract


interface EndpointProvider {
    fun endpoint(param: Any?, queryMap: HashMap<String, String> = hashMapOf()): String
}