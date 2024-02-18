package com.zp.st.history

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceHistory {
//    @GET("fetch_data_antro.php")
//    fun getHistory(
//        @Query("post_username") username: String,
//        @Query("post_password") password: String
//    ): Call<ArrayList<ResponseModelHistoryItem>>

    @GET("fetch_antro.php")
    fun getRiwayat(
        @Query("post_id") idUser: Int
    ): Call<ArrayList<ResponseModelHistoryItem>>
}