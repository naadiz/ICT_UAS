package com.zp.st.data.login


import com.google.gson.annotations.SerializedName

data class PayloadLogin(
    @SerializedName("data_anak")
    val dataAnak: List<DataAnak>,
    @SerializedName("email")
    val email: String,
    @SerializedName("id_user")
    val idUser: String,
    @SerializedName("jenis_kelamin")
    val jenisKelamin: String,
    @SerializedName("nama_belakang")
    val namaBelakang: String,
    @SerializedName("nama_depan")
    val namaDepan: String,
    @SerializedName("no_handphone")
    val noHandphone: String,
    @SerializedName("user_role")
    val userRole: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("tinggi_user")
    val tinggiUser: String,
    @SerializedName("berat_user")
    val beratUser: String,
    @SerializedName("usia_user")
    val usiaUser: String,
    @SerializedName("riwayat_user")
    val riwayatUser: String
)