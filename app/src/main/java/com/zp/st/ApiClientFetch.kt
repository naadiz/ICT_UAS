package com.zp.st

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientFetch {
    val BASE_FETCH = "https://unggah.esdsugm.id/fetch_antro/"
    val apiFetchService: ApiFetchService
    get(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_FETCH)
            .build()
        return retrofit.create(ApiFetchService::class.java)
    }
}