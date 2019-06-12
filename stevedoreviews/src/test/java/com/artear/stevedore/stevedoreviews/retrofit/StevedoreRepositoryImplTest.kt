package com.artear.stevedore.stevedoreviews.retrofit

import androidx.test.core.app.ApplicationProvider
import com.artear.networking.model.AndroidNetworking
import com.artear.stevedore.stevedoreviews.TestUtils
import com.artear.stevedore.stevedoreviews.TestUtils.Companion.log
import com.artear.stevedore.stevedoreviews.repository.contract.api.ApiStevedore
import com.artear.stevedore.stevedoreviews.repository.contract.domain.StevedoreRepository
import com.artear.stevedore.stevedoreviews.repository.impl.domain.StevedoreRepositoryImpl
import com.artear.stevedore.stevedoreviews.repository.model.Stevedore
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
class StevedoreRepositoryImplTest {

    companion object {
        const val folder = "stevedore"
        const val dynamicEndpoint = "stevedoreUrl"
    }

    private lateinit var androidNetworking: AndroidNetworking
    private lateinit var stevedoreRepository: StevedoreRepository

    private val request = Request.Builder().url("http://test.url").build()
    private val mediaType = MediaType.parse("application/json")

    @Mock
    lateinit var api: ApiStevedore

    @Mock
    lateinit var call: Call<Stevedore>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Timber.plant(mock(Timber.Tree::class.java))

        androidNetworking = spy(AndroidNetworking(ApplicationProvider.getApplicationContext()))
        stevedoreRepository = StevedoreRepositoryImpl(api, dynamicEndpoint, androidNetworking)

        `when`(api.getStevedore(dynamicEndpoint)).thenReturn(call)
        `when`(call.request()).thenReturn(request)
    }

    private fun initStevedore(file: String): Stevedore {
        val loader = javaClass.classLoader!!
        val stevedore = TestUtils.loadJSONFromAsset(loader, folder, file)!!
        val responseBodyOk = ResponseBody.create(mediaType, stevedore)
        return Gson().fromJson(responseBodyOk.string(), Stevedore::class.java)
    }

    @Test
    fun recipesResponseOk() {
        val stevedoreOk = initStevedore("stevedore_ok")
        `when`(call.execute()).thenReturn(Response.success(stevedoreOk))
        `when`(androidNetworking.isNetworkConnected()).thenReturn(true)
        val recipes = stevedoreRepository.stevedore()
        log("The size of recipes is = ${recipes.containers.size}")
        Assert.assertEquals(5, recipes.containers.size)
    }

    @Test
    fun recipesResponseOkWithoutOne() {
        val stevedoreWithoutOne = initStevedore("stevedore_container_style_null")
        `when`(call.execute()).thenReturn(Response.success(stevedoreWithoutOne))
        `when`(androidNetworking.isNetworkConnected()).thenReturn(true)
        val recipes = stevedoreRepository.stevedore()
        log("The size of recipes is = ${recipes.containers.size}, " +
                "because a style is null in one container")
        Assert.assertEquals(4, recipes.containers.size)
    }


    @Test(expected = BadResponseException::class)
    fun recipeInvalidResponse() {
        val responseBodyError = ResponseBody.create(mediaType, "{ \"error\":500, \"message\":\"Error test response\"")
        `when`(call.execute()).thenReturn(Response.error(500, responseBodyError))
        `when`(androidNetworking.isNetworkConnected()).thenReturn(true)
        stevedoreRepository.stevedore()
    }


    @Test(expected = IllegalStateException::class)
    fun recipesResponseOkNullBody() {
        `when`(call.execute()).thenReturn(Response.success(null))
        `when`(androidNetworking.isNetworkConnected()).thenReturn(true)
        stevedoreRepository.stevedore()
    }

    @Test(expected = NoInternetConnectionException::class)
    fun noInternetConnection() {
        `when`(androidNetworking.isNetworkConnected()).thenReturn(false)
        stevedoreRepository.stevedore()
    }


}