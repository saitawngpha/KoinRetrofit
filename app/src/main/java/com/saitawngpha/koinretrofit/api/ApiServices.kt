package com.saitawngpha.koinretrofit.api

import com.saitawngpha.koinretrofit.BuildConfig
import com.saitawngpha.koinretrofit.response.ResponsePhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 02/12/2023.
 */
interface ApiServices {

    @GET("api/")
    suspend fun getPhoto(
        @Query("q") searchQuery: String,
        @Query("image_type") imageType: String = "photo",
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ResponsePhoto>
}