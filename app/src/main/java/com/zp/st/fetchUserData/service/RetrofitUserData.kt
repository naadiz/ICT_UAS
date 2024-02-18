package com.zp.st.fetchUserData.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUserData {
    private const val BASE_URL_USER = "https://unggah.esdsugm.id/fetch_antro/"

    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private val client = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }.addInterceptor(logging)
        .build()


    val instance: ApiUserData by lazy {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL_USER).addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
        retrofit.create(ApiUserData::class.java)
    }
}