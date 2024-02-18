package com.zp.st.api.model.province


import com.google.gson.annotations.SerializedName

data class Provinsi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val nama: String
)