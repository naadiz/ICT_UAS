package com.zp.st.api.model.subdistrict


import com.google.gson.annotations.SerializedName

data class ResponseKel(
    @SerializedName("kelurahan")
    val kelurahan: List<Kelurahan>
)