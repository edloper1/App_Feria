package com.edwin_lopez.appferia.adapter

import com.edwin_lopez.appferia.api.CatApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CatApiService {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api: CatApi = retrofit.create(CatApi::class.java)
}

