package com.edwin_lopez.appferia.api

import com.edwin_lopez.appferia.model.CatImage
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("images/search")
    suspend fun getCatImages(
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int = 0
    ): List<CatImage>
}