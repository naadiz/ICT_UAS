package com.zp.st.fetchUserData.model


import com.google.gson.annotations.SerializedName

data class ResponsePayload(
    @SerializedName("data_anak")
    val responseDataAnak: List<ResponseDataAnak>? = null,
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
    val username: String
)