package com.zp.st.fetchUserData

import com.zp.st.fetchUserData.model.ResponsePayload
import com.zp.st.fetchUserData.service.RetrofitUserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataPresenterImpl(private val view: UserDataView) : UserDataPresenter{
    override fun onLoadData(username: String, password: String) {
        RetrofitUserData.instance.getUserData(username = username, password = password)
            .enqueue(object : Callback<ResponsePayload>{
                override fun onResponse(
                    call: Call<ResponsePayload>,
                    response: Response<ResponsePayload>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if(code == 200){
                        body?.let { view.onSuccess(it) }
                    } else {
                        view.onFailed()
                    }
                }

                override fun onFailure(call: Call<ResponsePayload>, t: Throwable) {
                    view.onFailed()
                }

            })
    }
}