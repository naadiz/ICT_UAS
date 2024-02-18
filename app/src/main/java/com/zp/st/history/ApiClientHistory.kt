package com.zp.st.history

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientHistory {
    val BASE_URL_HISTORY = "https://unggah.esdsugm.id/fetch_antro/"
    val apiServiceHistory: ApiServiceHistory
        get(){
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL_HISTORY)
                .build()
            return retrofit.create(ApiServiceHistory::class.java)
        }
}