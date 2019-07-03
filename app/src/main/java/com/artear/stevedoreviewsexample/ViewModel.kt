package com.artear.stevedoreviewsexample

import com.artear.domain.coroutine.SimpleReceiver
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreviews.presentation.PageParam
import com.artear.stevedore.stevedoreviews.repository.model.Paging
import com.artear.ui.model.State
import com.artear.ui.viewmodel.DynamicViewModel


class ViewModel : DynamicViewModel() {

    val list by lazy { newListData<ArtearItem>() }
    private val paging by lazy { newData<Paging>() }
    val refresh by lazy { newData<Boolean>() }

    private val receiver = SimpleReceiver(::onSuccess, ::defaultError)

    fun loadByCategory(categoryId: Int, getRecipes: GetRecipesByCategory) = requestLoading {

        val param = paging.value?.cursors?.before?.let {
            PageParam(categoryId, it)
        } ?: PageParam(categoryId)

        getRecipes(param, receiver)
    }

    fun loadNext(getRecipes: GetRecipes) {
        if (state.value != State.Loading) {
            paging.value?.cursors?.before?.let {
                requestLoading {
                    getRecipes(PageParam(page = it, size = 50), receiver)
                }
            }
        }
    }

    fun load(getRecipes: GetRecipes) {
        getRecipes.dispose()
        requestLoading {
            getRecipes(PageParam(), SimpleReceiver({
                if (!list.value.isNullOrEmpty()) refresh.value = true
                onSuccess(it)
            }, ::defaultError))
        }
    }

    private fun onSuccess(data: Pair<List<ArtearItem>, Paging?>) {
        list.value = data.first
        paging.value = data.second
        state.value = State.Success
    }

    override fun onCleared() {
        super.onCleared()

    }
}