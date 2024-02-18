package com.zp.st.api.model.subdistrict


import com.google.gson.annotations.SerializedName

data class Kelurahan(
    @SerializedName("id")
    val id: Long,
    @SerializedName("id_kecamatan")
    val idKecamatan: String,
    @SerializedName("nama")
    val nama: String
)