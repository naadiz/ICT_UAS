package com.zp.st.fetchUserData

import com.zp.st.fetchUserData.model.ResponsePayload

interface UserDataView {
    fun onSuccess(listData: ResponsePayload)
    fun onFailed()
}