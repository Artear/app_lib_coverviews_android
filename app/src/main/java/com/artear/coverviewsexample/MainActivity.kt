package com.artear.coverviewsexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.artear.cover.coveritem.presentation.adapter.ArtearOnClickListener
import com.artear.cover.coveritem.presentation.model.ArtearObject
import com.artear.cover.coveritem.repository.model.link.Link
import com.artear.cover.coverviews.GetCover
import com.artear.cover.coverviews.Manager
import com.artear.cover.coverviews.presentation.CoverRegister
import com.artear.cover.coverviews.presentation.adapter.CoverAdapter
import com.artear.cover.coverviews.repository.retrofit.CoverRepositoryImpl
import com.artear.cover.coverviews.repository.retrofit.RetrofitProvider
import com.artear.domain.coroutine.SimpleReceiver
import com.artear.networking.contract.Networking
import com.artear.networking.url.BaseUrl
import com.artear.networking.url.BaseUrlBuilder
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        init()
    }

    private fun init() {

        val manager = Manager()
        manager.registerTypeDeserializer()

        val urlBase = getBaseUrl()
        val retrofit = getRetrofit(urlBase)
        val coverRepository = CoverRepositoryImpl(retrofit, "cover", object : Networking {
            override fun isNetworkConnected(): Boolean {
                return true
            }
        })

        val onItemClickHandler = object : ArtearOnClickListener {
            override fun onArticleClick(link: Link) {
            }

            override fun onCategoryClick(link: Link) {
            }

            override fun onTagClick(link: Link) {
            }
        }

        val coverRegister = CoverRegister.Builder().coverRegister


        recyclerTest.adapter = CoverAdapter(coverRegister.adapters)

        val list = listOf<ArtearObject<*>>()


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

    private fun getRetrofit(baseUrl: BaseUrl): Retrofit {
        return RetrofitProvider(baseUrl).invoke()
    }

}