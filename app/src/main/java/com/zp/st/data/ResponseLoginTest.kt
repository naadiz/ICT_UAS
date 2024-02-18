package com.zp.st.data


import com.google.gson.annotations.SerializedName

data class ResponseLoginTest(
    @SerializedName("payload")
    val payloadLoginTest: PayloadLoginTest,
    @SerializedName("response")
    val response: Boolean
)