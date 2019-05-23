package com.artear.coverviewsexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artear.cover.coverviews.repository.contract.api.ApiCover
import com.artear.cover.coverviews.repository.impl.provider.ApiCoverHelper.getDefaultGsonMaker
import com.artear.cover.coverviews.repository.impl.provider.ApiCoverProvider
import com.artear.networking.model.AndroidNetworking
import com.artear.networking.url.BaseUrl
import com.artear.networking.url.BaseUrlBuilder

class MainActivity : AppCompatActivity() {

    private val androidNetworking by lazy { AndroidNetworking(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        init()
    }

    private fun init() {
//
//        val urlBase = getBaseUrl()
//        val api = getApi(urlBase)
//        val coverEndpoint = urlBase.toString() + "cover"
//
//        val coverRepository = CoverRepositoryImpl(api, coverEndpoint, androidNetworking)
//
//        val onItemClickHandler = object : ArticleOnClickListener {
//            override fun onArticleClick(link: Link) {
//            }
//        }
//
//        val adapters = listOf(ArticleItemAdapter(onItemClickHandler)
//
//                //        ,DfpItemAdapter()
//        )
//
//        recyclerTest.adapter = CoverAdapter(adapters)
//        recyclerTest.layoutManager = LinearLayoutManager(this)
//
//        val coverRegister = CoverRegister.Builder()
//                .add(BlockType.ARTICLE, ArticleShaper())
////                .add(BlockType.DFP, DfpShaper())
//                .build()
//
//        val getCover = GetCover(coverRegister, coverRepository)
//
//        getCover(SimpleReceiver({
//            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
//            (recyclerTest.adapter as CoverAdapter).setData(it)
//            messageHello.visibility = GONE
//        }, {
//            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//        }))
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