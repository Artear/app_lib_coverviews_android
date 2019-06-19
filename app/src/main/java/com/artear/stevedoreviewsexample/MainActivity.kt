package com.artear.stevedoreviewsexample

import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.artear.domain.coroutine.SimpleReceiver
import com.artear.networking.model.AndroidNetworking
import com.artear.networking.url.BaseUrl
import com.artear.networking.url.BaseUrlBuilder
import com.artear.stevedore.articleitem.ArticleItemAdapter
import com.artear.stevedore.articleitem.ArticleOnClickListener
import com.artear.stevedore.articleitem.ArticleShaper
import com.artear.stevedore.banneritem.DfpItemAdapter
import com.artear.stevedore.banneritem.DfpShaper
import com.artear.stevedore.categoryitem.presentation.CategoryShaper
import com.artear.stevedore.headeritem.presentation.HeaderShaper
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxType
import com.artear.stevedore.stevedoreitems.repository.model.link.Link
import com.artear.stevedore.stevedoreviews.GetStevedore
import com.artear.stevedore.stevedoreviews.presentation.StevedoreRegister
import com.artear.stevedore.stevedoreviews.presentation.adapter.StevedoreAdapter
import com.artear.stevedore.stevedoreviews.repository.ApiAction
import com.artear.stevedore.stevedoreviews.repository.contract.api.StevedoreApi
import com.artear.stevedore.stevedoreviews.repository.impl.domain.StevedoreRepositoryImpl
import com.artear.stevedore.stevedoreviews.repository.impl.provider.ApiStevedoreHelper.getDefaultGsonMaker
import com.artear.stevedore.stevedoreviews.repository.impl.provider.ApiStevedoreProvider
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val androidNetworking by lazy { AndroidNetworking(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val action = ApiAction(RecipesApiType(recipesApi))

        val stevedoreRepository = StevedoreRepositoryImpl(action, api, androidNetworking)

        val onItemClickHandler = object : ArticleOnClickListener {
            override fun onArticleClick(link: Link) {
            }
        }

        val adapters = listOf(ArticleItemAdapter(onItemClickHandler), DfpItemAdapter())

        recyclerTest.adapter = StevedoreAdapter(adapters)
        recyclerTest.layoutManager = LinearLayoutManager(this)

        val stevedoreRegister = StevedoreRegister.Builder()
                .addHeader(HeaderShaper())
                .add(BoxType.ARTICLE, ArticleShaper())
                .add(BoxType.DFP, DfpShaper())
                .add(BoxType.CATEGORY, CategoryShaper())
                .build()

        val getStevedore = GetStevedore(stevedoreRegister, stevedoreRepository)

        val getRecipes = GetRecipes(getStevedore)

        getRecipes.invoke(SimpleReceiver({
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            (recyclerTest.adapter as StevedoreAdapter).setData(it)
            messageHello.visibility = GONE
        }, {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            Timber.e("Error: %s", it.message)
        }))
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