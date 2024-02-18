package com.zp.st.data.login


import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("payload")
    val payloadLogin: PayloadLogin,
    @SerializedName("response")
    val response: Boolean
)