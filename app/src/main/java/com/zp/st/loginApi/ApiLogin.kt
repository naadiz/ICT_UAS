package com.zp.st.loginApi

import com.zp.st.data.login.ResponseLogin
import retrofit2.Call
import retrofit2.http.*

interface ApiLogin {
    @FormUrlEncoded
    @POST("login_esds.php")
    fun loginTest(
        @Field("post_username") username: String,
        @Field("post_password") password: String
    ): Call<ResponseLogin>
}