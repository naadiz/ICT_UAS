package com.zp.st.api.service

import com.zp.st.api.model.city.ResponseKota
import com.zp.st.api.model.district.ResponseKec
import com.zp.st.api.model.province.ResponseProvinsi
import com.zp.st.api.model.subdistrict.ResponseKel
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceArea {
    @GET("provinsi")
    fun getProvinsi() : Call<ResponseProvinsi>

    @GET("kota")
    fun getKota(@Query("id_provinsi") id_provinsi : Int) : Call<ResponseKota>

    @GET("kecamatan")
    fun getKec(@Query("id_kota") id_kota : Int) : Call<ResponseKec>

    @GET("kelurahan")
    fun getKel(@Query("id_kecamatan") id_kecamatan : Int) : Call<ResponseKel>
}