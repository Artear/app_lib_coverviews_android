package com.artear.stevedore.stevedoreviews.repository.contract.action


abstract class Action {
    abstract operator fun invoke(param: Any? = null): String
}