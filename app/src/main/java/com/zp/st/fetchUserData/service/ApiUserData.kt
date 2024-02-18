package com.zp.st.fetchUserData.service

import com.zp.st.fetchUserData.model.ResponsePayload
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUserData {
    @GET("fetch_user_esds_one.php")
    fun getUserData(
        @Query("post_username") username: String,
        @Query("post_password") password: String
    ): Call<ResponsePayload>
}