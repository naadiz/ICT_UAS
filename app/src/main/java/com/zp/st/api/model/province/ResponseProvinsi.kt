package com.zp.st.api.model.province


import com.google.gson.annotations.SerializedName

data class ResponseProvinsi(
    @SerializedName("provinsi")
    val provinsi: List<Provinsi>
)