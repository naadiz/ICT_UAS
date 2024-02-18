package com.zp.st


import com.google.gson.annotations.SerializedName

data class DataAntro(
    @SerializedName("berat_badan")
    val beratBadan: String,
    @SerializedName("id_alamat_anjungan")
    val idAlamatAnjungan: String,
    @SerializedName("id_anjungan")
    val idAnjungan: String,
    @SerializedName("id_pengukuran")
    val idPengukuran: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tanggal_ukur")
    val tanggalUkur: String,
    @SerializedName("tinggi_badan")
    val tinggiBadan: String
)