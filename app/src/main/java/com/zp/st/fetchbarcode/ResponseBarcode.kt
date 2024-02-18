package com.zp.st.fetchbarcode


import com.google.gson.annotations.SerializedName

data class ResponseBarcode(
    @SerializedName("data_antro")
    val dataAntro: List<DataAntro>
)