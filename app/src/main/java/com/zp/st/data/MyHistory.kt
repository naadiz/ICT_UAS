package com.zp.st.data

data class MyHistory(
    val date: String,
    val name: String,
    val gender: String,
    val dob: String,
    val tb_bb: String,
    var visibility: Boolean = false
)
