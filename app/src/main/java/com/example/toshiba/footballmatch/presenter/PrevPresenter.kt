package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import com.example.toshiba.footballmatch.model.ResponseMatch
import com.example.toshiba.footballmatch.model.ResponseMatchSearch
import retrofit2.Call
import retrofit2.Response

class PrevPresenter(val prevEvent: PrevView) {
    private var api: BaseApi? = UtilsApi.apiService

    fun getMatch(id: String) {
        api?.getMatchPrev(id)?.enqueue(object : retrofit2.Callback<ResponseMatch> {
            override fun onFailure(call: Call<ResponseMatch>?, t: Throwable?) {
                prevEvent.onFailure()
            }

            override fun onResponse(call: Call<ResponseMatch>?, response: Response<ResponseMatch>?) {
                response?.body()?.events?.let { prevEvent.onSuccess(it) }
            }

        })
    }

    fun getSearch(name: String) {
        api?.getSearchMatch(name)?.enqueue(object : retrofit2.Callback<ResponseMatchSearch> {
            override fun onFailure(call: Call<ResponseMatchSearch>?, t: Throwable?) {
                prevEvent.onFailure()
            }

            override fun onResponse(call: Call<ResponseMatchSearch>?, response: Response<ResponseMatchSearch>?) {
                response?.body()?.events?.let { prevEvent.onSuccess(it) }
            }

        })
    }
}