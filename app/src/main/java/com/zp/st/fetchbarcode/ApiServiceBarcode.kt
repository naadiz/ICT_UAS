package com.zp.st.fetchbarcode

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceBarcode {
    @GET("barcode.php")
    fun getBarcode(
        @Query("post_id") idUser: Int
    ): Call<ResponseBarcode>
}