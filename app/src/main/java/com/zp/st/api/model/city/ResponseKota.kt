package com.zp.st.api.model.city

import com.google.gson.annotations.SerializedName

data class ResponseKota(
    @SerializedName("kota_kabupaten")
    val kotaKabupaten: List<KotaKabupaten>
)