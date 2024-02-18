package com.zp.st.fetchUserData

interface UserDataPresenter {
    fun onLoadData(username: String, password: String)
}