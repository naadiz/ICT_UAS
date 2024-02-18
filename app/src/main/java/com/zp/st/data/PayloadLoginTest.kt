package com.zp.st.data


import com.google.gson.annotations.SerializedName

data class PayloadLoginTest(
    @SerializedName("email") // OK
    val email: String,
    @SerializedName("id_user")
    val idUser: String,
    @SerializedName("jenis_kelamin") // OK
    val jenisKelamin: String,
    @SerializedName("nama_belakang") // OK
    val namaBelakang: String,
    @SerializedName("nama_depan") // OK
    val namaDepan: String,
    @SerializedName("no_handphone") // OK
    val noHandphone: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("username") // OK
    val username: String
)