package com.zp.st.loginApi.signup

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiSignup {
    @FormUrlEncoded
    @POST("signup.php")
    fun toSignUp(
        @Field("new_role") role: Int = 1,
        @Field("new_username") username: String,
        @Field("new_email") email: String,
        @Field("new_no_handphone") noHandphone: String,
        @Field("new_password") password: String,
        @Field("new_nama_depan") namaDepan: String,
        @Field("new_nama_belakang") namaBelakang: String,
        @Field("new_jenis_kelamin") jenisKelamin: String,
        @Field("new_id_provinsi") idProvinsi: String,
        @Field("new_id_kabupaten_kota") idKabupatenKota: String,
        @Field("new_id_kecamatan") idKecamatan: String,
        @Field("new_id_kelurahan") idKelurahan: String,
        @Field("new_rukun_warga") rukunWarga: String,
        @Field("new_rukun_tetangga") rukunTetangga: String,
        @Field("new_nama_jalan") namaJalan: String,
        @Field("new_id_kode_pos") idKodePos: String,
        @Field("new_active") active: Int = 1,
        @Field("new_usia_user") usiaUser: Int,
        @Field("new_tinggi_user") tinggiUser: Int,
        @Field("new_berat_user") beratUser: Int,
        @Field("new_riwayat_user") riwayatUser: String,
        @Field("new_no_nik") noNik: String,
    ): Call<ResponseModeSignup>
}