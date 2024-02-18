package com.zp.st.data.login

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_anak")
data class DataAnak(
    @SerializedName("anak_ke")
    val anakKe: String,
//    @SerializedName("data_antro")
//    val dataAntro: List<DataAntro>,
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