package com.zp.st

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFetchService {
    @GET("fetch_user_esds.php")
    fun getUsers(
        @Query("post_username") username: String,
        @Query("post_password") password: String
    ): Call<ArrayList<ResponseModel>>
}