package com.artear.stevedore.stevedoreviews.repository.contract.action

import com.artear.stevedore.stevedoreviews.presentation.PageParam
import com.artear.stevedore.stevedoreviews.repository.contract.EndpointProvider


class ApiAction(private val endpointProvider: EndpointProvider) : Action() {
    override fun invoke(param: Any?): String {
        return if (param is PageParam<*>) {
            val queryMap = hashMapOf<String, String>()
            param.page?.let {
                queryMap["before"] = it
            }
            param.size?.let {
                queryMap["count"] = it.toString()
            }
            endpointProvider.endpoint(param.param, queryMap)
        } else {
            endpointProvider.endpoint(param)
        }

    }
}