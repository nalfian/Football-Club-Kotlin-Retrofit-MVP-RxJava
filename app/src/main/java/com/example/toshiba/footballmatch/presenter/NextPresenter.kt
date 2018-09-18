package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import com.example.toshiba.footballmatch.model.ResponseMatch
import retrofit2.Call
import retrofit2.Response

class NextPresenter(val nextView: NextView) {
    private var api: BaseApi? = null
    fun getMatch() {
        api = UtilsApi.apiService
        api?.getMatchNext()?.enqueue(object : retrofit2.Callback<ResponseMatch> {
            override fun onFailure(call: Call<ResponseMatch>?, t: Throwable?) {
                nextView.onFailure()
            }

            override fun onResponse(call: Call<ResponseMatch>?, response: Response<ResponseMatch>?) {
                response?.body()?.events?.let { nextView.onSuccess(it) }
            }

        })
    }
}