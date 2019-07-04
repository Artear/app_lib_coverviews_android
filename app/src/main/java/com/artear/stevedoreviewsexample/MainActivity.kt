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
import com.artear.stevedore.articleitem.ArticleItemAdapter
import com.artear.stevedore.articleitem.ArticleOnClickListener
import com.artear.stevedore.articleitem.ArticleShaper
import com.artear.stevedore.banneritem.DfpItemAdapter
import com.artear.stevedore.banneritem.DfpShaper
import com.artear.stevedore.categoryitem.presentation.CategoryAdapter
import com.artear.stevedore.categoryitem.presentation.CategoryShaper
import com.artear.stevedore.headeritem.presentation.HeaderShaper
import com.artear.stevedore.mediaitem.presentation.MediaItemAdapter
import com.artear.stevedore.mediaitem.presentation.MediaItemShaper
import com.artear.stevedore.stevedoreitems.presentation.adapter.ContentAdapter
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearSection
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
    private lateinit var viewModel: ViewModel
    private lateinit var getRecipes: GetRecipes


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.main_activity)
        init()
    }

    private fun init() {

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

        val pagingReloadListener = object : PagingErrorView.OnReloadClickListener {
            override fun onReload() {
                viewModel.loadNext(getRecipes)
            }
        }

        val adapters = listOf(
                ArticleItemAdapter(onArticleItemClickHandler),
                DfpItemAdapter(),
                CategoryAdapter(),
                MediaItemAdapter(),
                LoadingItemAdapter(pagingReloadListener)
        )

        recyclerTest.adapter = StevedoreAdapter(adapters)
        recyclerTest.layoutManager = LinearLayoutManager(this)

        val stevedoreRegister = StevedoreRegister.Builder()
                .addHeader(HeaderShaper())
                .add(BoxType.ARTICLE, ArticleShaper())
                .add(BoxType.DFP, DfpShaper())
                .add(BoxType.CATEGORY, CategoryShaper())
                .add(BoxType.MEDIA, MediaItemShaper())
                .build()

        val getStevedore = GetStevedore(stevedoreRegister, stevedoreRepository)

        getRecipes = GetRecipes(getStevedore)

        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(application).create(ViewModel::class.java)

        val listener = object : EndlessRecyclerViewScrollListener(recyclerTest.layoutManager!!) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.loadNext(getRecipes)
            }
        }

        recyclerTest.addOnScrollListener(listener)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.load(getRecipes)
        }

        viewModel.list.observe(this, Observer {
            onDataChanged(it)
        })

        viewModel.state.observe(this, Observer {
            uiStateViewModel.state.value = it
        })

        viewModel.refreshed.observe(this, Observer {
            listener.resetState()
            (recyclerTest.adapter as ContentAdapter).clear()
        })

        viewModel.pagingState.observe(this, Observer {
            when (it) {
                is State.Loading -> onNextLoading()
                is State.Success -> onNextSuccess()
                is State.Error -> onNextError(it.error)
            }
        })

        viewModel.load(getRecipes)
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
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        Timber.e("Error: %s", error.message)
    }

    private fun onNextLoading() {
        //TODO //Add or change to loading if exist
        val contentAdapter = (recyclerTest.adapter as ContentAdapter)
        val position = contentAdapter.list.indexOfFirst { it.model is LoadingData }

        if (position == -1) {
            contentAdapter.list.add(ArtearItem(LoadingData(), ArtearSection()))
            contentAdapter.notifyDataSetChanged()
        } else {
            val loadingItemAdapter = contentAdapter.getItemAdapter(position) as LoadingItemAdapter
            val loadingViewHolder = loadingItemAdapter.viewHolder as LoadingViewHolder
            loadingViewHolder.setLoading()
        }
    }

    private fun onNextSuccess() {
        //TODO //Remove from list
        val contentAdapter = (recyclerTest.adapter as ContentAdapter)
        contentAdapter.list.indexOfFirst { it.model is LoadingData }
                .let { contentAdapter.list.removeAt(it) }
        contentAdapter.notifyDataSetChanged()
    }

    private fun onNextError(error: NestError) {
        //TODO //setErrorType to object list
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