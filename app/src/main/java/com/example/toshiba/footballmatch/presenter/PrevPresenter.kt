package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import com.example.toshiba.footballmatch.model.ResponseMatch
import retrofit2.Call
import retrofit2.Response

class PrevPresenter(val prevEvent: PrevView) {
    private var api: BaseApi? = null
    fun getMatch() {
        api = UtilsApi.apiService
        api?.getMatchPrev()?.enqueue(object : retrofit2.Callback<ResponseMatch> {
            override fun onFailure(call: Call<ResponseMatch>?, t: Throwable?) {
                prevEvent.onFailure()
            }

            override fun onResponse(call: Call<ResponseMatch>?, response: Response<ResponseMatch>?) {
                response?.body()?.events?.let { prevEvent.onSuccess(it) }
            }

        })
    }
}