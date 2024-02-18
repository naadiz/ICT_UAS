package com.zp.st.fetchUserData.model


import com.google.gson.annotations.SerializedName

data class ResponseUser(
    @SerializedName("payload")
    val responsePayload: ResponsePayload,
    @SerializedName("response")
    val response: Boolean
)