package com.iesperemaria.modulointerlunar.deliiciouswaitress.core

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiApiClient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit():DeliiApiClient{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeliiApiClient::class.java)
    }
}