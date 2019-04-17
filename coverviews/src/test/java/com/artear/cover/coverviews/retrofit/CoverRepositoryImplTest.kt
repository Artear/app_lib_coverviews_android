package com.artear.cover.coverviews.retrofit

import androidx.test.core.app.ApplicationProvider
import com.artear.cover.coverviews.TestUtils.Companion.loadJSONFromAsset
import com.artear.cover.coverviews.TestUtils.Companion.log
import com.artear.cover.coverviews.repository.contract.api.ApiCover
import com.artear.cover.coverviews.repository.contract.domain.CoverRepository
import com.artear.cover.coverviews.repository.impl.domain.CoverRepositoryImpl
import com.artear.cover.coverviews.repository.model.Stevedore
import com.artear.networking.model.AndroidNetworking
import com.artear.tools.exception.BadResponseException
import com.artear.tools.exception.NoInternetConnectionException
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber



@RunWith(RobolectricTestRunner::class)
class CoverRepositoryImplTest {

    private lateinit var stevedore: Stevedore
    private lateinit var androidNetworking: AndroidNetworking
    private lateinit var coverRepository: CoverRepository

    private val request = Request.Builder().url("http://test.url").build()
    private val mediaType = MediaType.parse("application/json")

    @Mock
    lateinit var api: ApiCover

    @Mock
    lateinit var call: Call<Stevedore>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Timber.plant(mock(Timber.Tree::class.java))

        val loader = javaClass.classLoader!!
        val stevedoreJson = loadJSONFromAsset(loader, "stevedore", "cover")!!

        val responseBodyOk = ResponseBody.create(mediaType, stevedoreJson)
        stevedore = Gson().fromJson(responseBodyOk.string(), Stevedore::class.java)

        androidNetworking = spy(AndroidNetworking(ApplicationProvider.getApplicationContext()))
        coverRepository = CoverRepositoryImpl(api, "coverUrl", androidNetworking)

        `when`(api.getCover("coverUrl")).thenReturn(call)
        `when`(call.request()).thenReturn(request)
    }

    @Test
    fun recipesResponseOk() {
        `when`(call.execute()).thenReturn(Response.success(stevedore))
        `when`(androidNetworking.isNetworkConnected()).thenReturn(true)
        val recipes = coverRepository.cover()
        log("The size of recipes is = ${recipes.containers.size}")
        Assert.assertEquals(5, recipes.containers.size)
    }


    @Test(expected = BadResponseException::class)
    fun recipeInvalidResponse() {
        val responseBodyError = ResponseBody.create(mediaType, "{ \"error\":500, \"message\":\"Error test response\"")
        `when`(call.execute()).thenReturn(Response.error(500, responseBodyError))
        `when`(androidNetworking.isNetworkConnected()).thenReturn(true)
        coverRepository.cover()
    }


    @Test(expected = IllegalStateException::class)
    fun recipesResponseOkNullBody() {
        `when`(call.execute()).thenReturn(Response.success(null))
        `when`(androidNetworking.isNetworkConnected()).thenReturn(true)
        coverRepository.cover()
    }

    @Test(expected = NoInternetConnectionException::class)
    fun noInternetConnection() {
        `when`(androidNetworking.isNetworkConnected()).thenReturn(false)
        coverRepository.cover()
    }

}