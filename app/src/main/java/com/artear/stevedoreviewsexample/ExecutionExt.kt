package com.artear.stevedoreviewsexample

import com.artear.domain.coroutine.SimpleReceiver
import com.artear.domain.coroutine.async
import com.artear.domain.coroutine.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers


fun <Return> executeAsync(receiver: SimpleReceiver<Return>,
                          executable: suspend CoroutineScope.() -> Return): Deferred<Return>? {
    val deferred = async(Dispatchers.IO) { executable() }
    launch(Dispatchers.Main) {
        try {
            val result = deferred!!.await()
            receiver.onSuccess(result)
        } catch (ex: Exception) {
            receiver.onError(ex)
        }
    }
    return deferred
}