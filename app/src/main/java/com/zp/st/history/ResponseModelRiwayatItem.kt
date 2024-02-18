package com.zp.st.history


import com.google.gson.annotations.SerializedName

data class ResponseModelRiwayatItem(
    @SerializedName("berat")
    val berat: String,
    @SerializedName("hasil")
    val hasil: String
)