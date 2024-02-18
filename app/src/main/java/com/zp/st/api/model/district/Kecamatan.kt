package com.zp.st.api.model.district


import com.google.gson.annotations.SerializedName

data class Kecamatan(
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_kota")
    val idKota: String,
    @SerializedName("nama")
    val nama: String
)