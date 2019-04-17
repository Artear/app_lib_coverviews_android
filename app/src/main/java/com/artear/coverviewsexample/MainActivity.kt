package com.artear.coverviewsexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.artear.cover.articleitem.ArticleItemAdapter
import com.artear.cover.articleitem.ArticleOnClickListener
import com.artear.cover.articleitem.ArticleShaper
import com.artear.cover.banneritem.DfpItemAdapter
import com.artear.cover.banneritem.DfpShaper
import com.artear.cover.coveritem.repository.model.block.BlockType
import com.artear.cover.coveritem.repository.model.link.Link
import com.artear.cover.coverviews.GetCover
import com.artear.cover.coverviews.presentation.CoverRegister
import com.artear.cover.coverviews.presentation.adapter.CoverAdapter
import com.artear.cover.coverviews.repository.contract.api.ApiCover
import com.artear.cover.coverviews.repository.impl.domain.CoverRepositoryImpl
import com.artear.cover.coverviews.repository.impl.provider.ApiCoverHelper.getDefaultGsonMaker
import com.artear.cover.coverviews.repository.impl.provider.ApiCoverProvider
import com.artear.domain.coroutine.SimpleReceiver
import com.artear.networking.model.AndroidNetworking
import com.artear.networking.url.BaseUrl
import com.artear.networking.url.BaseUrlBuilder
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val androidNetworking by lazy { AndroidNetworking(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        init()
    }

    private fun init() {

        val urlBase = getBaseUrl()
        val api = getApi(urlBase)
        val coverEndpoint = urlBase.toString() + "cover"

        val coverRepository = CoverRepositoryImpl(api, coverEndpoint, androidNetworking)

        val onItemClickHandler = object : ArticleOnClickListener {
            override fun onArticleClick(link: Link) {
            }
        }

        val coverRegister = CoverRegister.Builder()
                .add(BlockType.ARTICLE, ArticleShaper(), ArticleItemAdapter(onItemClickHandler))
                .add(BlockType.DFP, DfpShaper(), DfpItemAdapter())
                .build()

        recyclerTest.adapter = CoverAdapter(coverRegister.adapters)
        recyclerTest.layoutManager = LinearLayoutManager(this)

        val getCover = GetCover(coverRegister, coverRepository)

        getCover(SimpleReceiver({
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            (recyclerTest.adapter as CoverAdapter).setData(it)
        }, {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }))
    }

    private fun getBaseUrl(): BaseUrl {
        return BaseUrlBuilder()
                .addScheme("https")
                .addHost("cucinare.tv/wp-json/cucinare")
                .addVersion("v1")
                .build()
    }

    private fun getApi(baseUrl: BaseUrl): ApiCover {
        return ApiCoverProvider(baseUrl, getDefaultGsonMaker()).invoke()

    }

}