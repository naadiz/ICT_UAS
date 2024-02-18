package com.zp.st.api.model.district


import com.google.gson.annotations.SerializedName

data class ResponseKec(
    @SerializedName("kecamatan")
    val kecamatan: List<Kecamatan>
)