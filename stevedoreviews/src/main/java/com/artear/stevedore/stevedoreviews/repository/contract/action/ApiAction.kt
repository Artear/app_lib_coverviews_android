package com.artear.stevedore.stevedoreviews.repository.contract.action

import com.artear.stevedore.stevedoreviews.repository.contract.EndpointProvider


class ApiAction(private val endpointProvider: EndpointProvider) : Action() {
    override fun invoke(param: Any?): String = endpointProvider.endpoint(param)
}