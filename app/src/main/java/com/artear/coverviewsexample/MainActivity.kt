package com.artear.coverviewsexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artear.cover.coverviews.GetCover
import com.artear.cover.coverviews.Manager
import com.artear.cover.coverviews.repository.retrofit.CoverRepositoryImpl
import com.artear.networking.contract.Networking
import com.artear.networking.url.BaseUrl
import com.artear.networking.url.BaseUrlBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        GetCover(coverRepository)

    }

    private fun getBaseUrl(): BaseUrl {
        return BaseUrlBuilder()
                .addScheme("https")
                .addHost("cucinare.tv/wp-json/cucinare")
                .addVersion("v1")
                .build()
    }

    private fun getRetrofit(baseUrl: BaseUrl): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl.toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}