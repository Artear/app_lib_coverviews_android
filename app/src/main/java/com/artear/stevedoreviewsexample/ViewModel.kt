package com.artear.stevedoreviewsexample

import androidx.lifecycle.MutableLiveData
import com.artear.domain.coroutine.SimpleReceiver
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreviews.presentation.PageParam
import com.artear.stevedore.stevedoreviews.repository.model.Paging
import com.artear.ui.model.State
import com.artear.ui.viewmodel.DynamicViewModel


class ViewModel : DynamicViewModel() {

    val list by lazy { newListData<ArtearItem>() }
    val refreshed by lazy { newData<Boolean>() }

    val pagingState by lazy { newData<State>() }
    private val paging by lazy { newData<Paging>() }

    fun loadByCategory(categoryId: Int, getRecipes: GetRecipesByCategory) = requestLoading {

        val param = paging.value?.cursors?.before?.let {
            PageParam(categoryId, it)
        } ?: PageParam(categoryId)

        getRecipes(param, SimpleReceiver({ onSuccess(it, state) }, ::defaultError))
    }

    fun loadNext(getRecipes: GetRecipes) {
        if (pagingState.value != State.Loading) {
            paging.value?.cursors?.before?.let {
                pagingState.value = State.Loading
                val param = PageParam<Void>(page = it, size = 50)
                getRecipes(param, SimpleReceiver({ result ->
                    onSuccess(result, pagingState)
                }, { error ->
                    pagingState.value = State.Error(error)
                }))
            }
        }
    }

    fun load(getRecipes: GetRecipes) {
        getRecipes.dispose()
        requestLoading {
            getRecipes(PageParam(), SimpleReceiver({
                if (!list.value.isNullOrEmpty()) refreshed.value = true
                onSuccess(it, state)
            }, ::defaultError))
        }
    }

    private fun onSuccess(data: Pair<List<ArtearItem>, Paging?>, state: MutableLiveData<State>) {
        list.value = data.first
        paging.value = data.second
        state.value = State.Success
    }

}