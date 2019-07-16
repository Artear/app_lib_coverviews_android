package com.artear.stevedoreviewsexample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artear.networking.model.AndroidNetworking
import com.artear.networking.url.BaseUrl
import com.artear.networking.url.BaseUrlBuilder
import com.artear.stevedore.articleitem.presentation.ArticleItemAdapter
import com.artear.stevedore.articleitem.presentation.ArticleItemShaper
import com.artear.stevedore.articleitem.presentation.ArticleOnClickListener
import com.artear.stevedore.banneritem.presentation.DfpItemAdapter
import com.artear.stevedore.banneritem.presentation.DfpShaper
import com.artear.stevedore.categoryitem.presentation.CategoryAdapter
import com.artear.stevedore.categoryitem.presentation.CategoryOnClickListener
import com.artear.stevedore.categoryitem.presentation.CategoryShaper
import com.artear.stevedore.headeritem.presentation.HeaderItemAdapter
import com.artear.stevedore.headeritem.presentation.HeaderShaper
import com.artear.stevedore.mediaitem.presentation.MediaItemAdapter
import com.artear.stevedore.mediaitem.presentation.MediaItemShaper
import com.artear.stevedore.stevedoreitems.presentation.adapter.ContentAdapter
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxType
import com.artear.stevedore.stevedoreitems.repository.model.link.Link
import com.artear.stevedore.stevedoreviews.GetStevedore
import com.artear.stevedore.stevedoreviews.presentation.StevedoreRegister
import com.artear.stevedore.stevedoreviews.presentation.adapter.StevedoreAdapter
import com.artear.stevedore.stevedoreviews.presentation.util.EndlessRecyclerViewScrollListener
import com.artear.stevedore.stevedoreviews.repository.contract.action.ApiAction
import com.artear.stevedore.stevedoreviews.repository.contract.api.StevedoreApi
import com.artear.stevedore.stevedoreviews.repository.impl.domain.StevedoreRepositoryImpl
import com.artear.stevedore.stevedoreviews.repository.impl.provider.ApiStevedoreHelper.getDefaultGsonMaker
import com.artear.stevedore.stevedoreviews.repository.impl.provider.ApiStevedoreProvider
import com.artear.stevedoreviewsexample.contract.RecipesApi
import com.artear.stevedoreviewsexample.contract.impl.RecipesEP
import com.artear.stevedoreviewsexample.loading.LoadingData
import com.artear.stevedoreviewsexample.loading.LoadingItemAdapter
import com.artear.stevedoreviewsexample.loading.LoadingViewHolder
import com.artear.stevedoreviewsexample.usecase.GetRecipes
import com.artear.tools.error.NestError
import com.artear.ui.base.ArtearActivity
import com.artear.ui.model.State
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import timber.log.Timber

class MainActivity : ArtearActivity() {

    private val androidNetworking by lazy { AndroidNetworking(this) }
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var getRecipes: GetRecipes


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.main_activity)
        init()
    }

    private fun init() {

        initUseCase()

        recipesViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(application).create(RecipesViewModel::class.java)

        val listener = object : EndlessRecyclerViewScrollListener(recyclerTest.layoutManager!!) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                recipesViewModel.loadNext(getRecipes)
            }
        }

        recyclerTest.addOnScrollListener(listener)

        swipeRefreshLayout.setOnRefreshListener {
            recipesViewModel.load(getRecipes)
        }

        setViewModelObservers(listener)

        recipesViewModel.load(getRecipes)
    }

    private fun initUseCase() {

        val api = getApi()
        val recipesApi = Retrofit.Builder()
                .baseUrl(getBaseUrl().toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<RecipesApi>()

        val action = ApiAction(RecipesEP(recipesApi))

        val stevedoreRepository = StevedoreRepositoryImpl(action, api, androidNetworking)

        val onArticleItemClickHandler = object : ArticleOnClickListener {
            override fun onArticleClick(link: Link) {
            }
        }
        val onCategoryItemClickHandler = object : CategoryOnClickListener {
            override fun onCategoryClick(link: Link) {

            }
        }

        val pagingReloadListener = object : PagingErrorView.OnReloadClickListener {
            override fun onReload() {
                recipesViewModel.loadNext(getRecipes)
            }
        }

        val adapters = listOf(
                ArticleItemAdapter(onArticleItemClickHandler),
                DfpItemAdapter(),
                CategoryAdapter(onCategoryItemClickHandler),
                MediaItemAdapter(),
                HeaderItemAdapter(),
                LoadingItemAdapter(pagingReloadListener)
        )

        recyclerTest.adapter = StevedoreAdapter(adapters)
        recyclerTest.layoutManager = LinearLayoutManager(this)
        recyclerTest.addItemDecoration(GridSpacingItemDecoration())

        val stevedoreRegister = StevedoreRegister.Builder()
                .addHeader(HeaderShaper())
                .add(BoxType.ARTICLE, ArticleItemShaper())
                .add(BoxType.DFP, DfpShaper())
                .add(BoxType.CATEGORY, CategoryShaper())
                .add(BoxType.MEDIA, MediaItemShaper())
                .build()

        val getStevedore = GetStevedore(stevedoreRegister, stevedoreRepository)

        getRecipes = GetRecipes(getStevedore)
    }

    private fun setViewModelObservers(listener: EndlessRecyclerViewScrollListener) {
        recipesViewModel.list.observe(this, Observer {
            onDataChanged(it)
        })

        recipesViewModel.state.observe(this, Observer {
            uiStateViewModel.state.value = it
        })

        recipesViewModel.refreshed.observe(this, Observer {
            listener.resetState()
            (recyclerTest.adapter as ContentAdapter).clear()
        })

        recipesViewModel.pagingState.observe(this, Observer {
            when (it) {
                is State.Loading -> onNextLoading()
                is State.Success -> onNextSuccess()
                is State.Error -> onNextError(it.error)
            }
        })
    }

    private fun onDataChanged(it: List<ArtearItem>) {
        (recyclerTest.adapter as StevedoreAdapter).addData(it)
        messageHello.visibility = View.GONE
    }

    override fun onLoading() {
        super.onLoading()
    }

    override fun onSuccess() {
        super.onSuccess()
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onError(error: NestError) {
        super.onError(error)
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        Timber.e("Error: %s", error.message)
    }

    private fun onNextLoading() {
        recyclerTest.post {
            val contentAdapter = (recyclerTest.adapter as ContentAdapter)
            val position = contentAdapter.list.indexOfFirst { it.model is LoadingData }
            if (position != -1) {
                val loadingItemAdapter = contentAdapter.getItemAdapter(position) as LoadingItemAdapter
                val loadingViewHolder = loadingItemAdapter.viewHolder as? LoadingViewHolder
                loadingViewHolder?.setLoading()
            }
        }
    }

    private fun onNextSuccess() {
        //Remove from list
        val contentAdapter = (recyclerTest.adapter as ContentAdapter)
        contentAdapter.list.indexOfFirst { it.model is LoadingData }
                .let { contentAdapter.list.removeAt(it) }
        contentAdapter.notifyDataSetChanged()
    }

    private fun onNextError(error: NestError) {
        //setErrorType to object list
        val contentAdapter = (recyclerTest.adapter as ContentAdapter)
        val position = contentAdapter.list.indexOfFirst { it.model is LoadingData }
        val loadingItemAdapter = contentAdapter.getItemAdapter(position) as LoadingItemAdapter
        val loadingViewHolder = loadingItemAdapter.viewHolder as LoadingViewHolder
        loadingViewHolder.setError(error)
    }


    private fun getBaseUrl(): BaseUrl {
        return BaseUrlBuilder()
                .addScheme("https")
                .addHost("stg.cucinare.tv/wp-json/cucinare")
                .addVersion("1.0")
                .build()
    }

    private fun getApi(): StevedoreApi {
        return ApiStevedoreProvider(getBaseUrl(), getDefaultGsonMaker()).invoke()

    }

}
