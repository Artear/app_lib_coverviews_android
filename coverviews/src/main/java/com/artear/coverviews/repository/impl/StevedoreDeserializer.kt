package com.artear.coverviews.repository.impl

import com.artear.coveritem.deserializer.getModelList
import com.artear.coverviews.repository.model.container.Container
import com.artear.coverviews.repository.model.container.Stevedore
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import timber.log.Timber
import java.lang.reflect.Type

class StevedoreDeserializer : JsonDeserializer<Stevedore> {

    companion object {
        const val CONTAINERS = "containers"
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Stevedore {
        Timber.d("Start Stevedore Deserialize")
        val containers = json.getModelList(CONTAINERS, context, Container::class)
        return Stevedore(containers)
    }

}


