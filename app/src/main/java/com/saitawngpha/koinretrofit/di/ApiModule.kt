package com.saitawngpha.koinretrofit.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.saitawngpha.koinretrofit.BuildConfig
import com.saitawngpha.koinretrofit.api.ApiServices
import com.saitawngpha.koinretrofit.utils.Constants.BASE_URL
import com.saitawngpha.koinretrofit.utils.Constants.NETWORK_TIMEOUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 02/12/2023.
 */

const val baseUrl = BASE_URL
const val networkTimeout = NETWORK_TIMEOUT

fun provideGson(): Gson = GsonBuilder().setLenient().create()

fun provideOkHttpClient() = if (BuildConfig.DEBUG){
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val requestInterceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)
    }

    OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(requestInterceptor)
        .build()
}else {
    OkHttpClient
        .Builder()
        .build()
}

fun provideRetrofit(
    baseUrl: String,
    gson: Gson,
    client: OkHttpClient
) : ApiServices = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
    .create(ApiServices::class.java)

val apiModule = module {
    single { baseUrl }
    single { networkTimeout }
    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get(), get()) }
}
