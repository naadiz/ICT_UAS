package com.zp.st.history

import com.google.gson.annotations.SerializedName

data class ResponseModelHistoryItem(
    @SerializedName("berat_badan")
    val berat: String,
    @SerializedName("tinggi_badan")
    val hasil: String,
    @SerializedName("id_anak")
    val idAnak: String,
    @SerializedName("jenis_kelamin_anak")
    val jenisKelaminAnak: String,
    @SerializedName("nama_anak")
    val namaAnak: String,
    @SerializedName("usia_anak")
    val usiaAnak: String,
    var visibility: Boolean = false
)