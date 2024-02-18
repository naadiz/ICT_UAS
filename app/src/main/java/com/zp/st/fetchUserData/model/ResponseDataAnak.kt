package com.zp.st.fetchUserData.model


import com.google.gson.annotations.SerializedName

data class ResponseDataAnak(
    @SerializedName("anak_ke")
    val anakKe: String,
    @SerializedName("data_antro")
    val responseDataAntro: List<ResponseDataAntro>,
    @SerializedName("id_anak")
    val idAnak: String,
    @SerializedName("jenis_kelamin_anak")
    val jenisKelaminAnak: String,
    @SerializedName("nama_anak")
    val namaAnak: String,
    @SerializedName("tanggal_lahir")
    val tanggalLahir: String,
    @SerializedName("tempat_lahir")
    val tempatLahir: String
)