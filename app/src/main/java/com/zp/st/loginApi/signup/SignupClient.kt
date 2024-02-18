package com.zp.st.loginApi.signup

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignupClient {
    val BASE_FETCH = "https://unggah.esdsugm.id/fetch_antro/"
    val apiSignup: ApiSignup
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
            return retrofit.create(ApiSignup::class.java)
        }
}