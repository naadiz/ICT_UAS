package com.zp.st.loginApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginClient {
    private fun getRetrofitClient(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://unggah.esdsugm.id/fetch_antro/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstanse(): ApiLogin{
        return getRetrofitClient().create(ApiLogin::class.java)
    }
}