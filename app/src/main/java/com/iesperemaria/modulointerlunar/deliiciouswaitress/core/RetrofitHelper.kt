package com.iesperemaria.modulointerlunar.deliiciouswaitress.core

import android.util.Log
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiApiClient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.Constants.BASE_URL
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private var mtoken: String = ""
    private val httpClient =  OkHttpClient.Builder()
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private lateinit var deliiApiClient: DeliiApiClient

    fun getDeliiApiClient():DeliiApiClient{
        if (!this::deliiApiClient.isInitialized)
            deliiApiClient = builder.build().create(DeliiApiClient::class.java)
        return deliiApiClient
    }

    fun setToken(token: String) {
        mtoken = token
        Log.i("RetrofitHelper", "This is my new token: $mtoken")
        if (mtoken.isNotEmpty()) {
            val interceptor = AuthenticationInterceptor("Bearer $mtoken")
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                builder.client(httpClient.build())
            }
        }
        deliiApiClient = builder.build().create(DeliiApiClient::class.java)
    }
}