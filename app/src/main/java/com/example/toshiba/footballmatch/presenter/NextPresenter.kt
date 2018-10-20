package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.ResponseMatch
import com.example.toshiba.footballmatch.model.ResponseMatchSearch
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import retrofit2.Call
import retrofit2.Response

class NextPresenter(val nextView: NextView) {
    private var api: BaseApi? = UtilsApi.apiService

    fun getMatch(id: String) {
        api?.getMatchNext(id)?.enqueue(object : retrofit2.Callback<ResponseMatch> {
            override fun onFailure(call: Call<ResponseMatch>?, t: Throwable?) {
                nextView.onFailure()
            }

            override fun onResponse(call: Call<ResponseMatch>?, response: Response<ResponseMatch>?) {
                response?.body()?.events?.let { nextView.onSuccess(it) }
            }

        })
    }

    fun getSearch(name: String) {
        api?.getSearchMatch(name)?.enqueue(object : retrofit2.Callback<ResponseMatchSearch> {
            override fun onFailure(call: Call<ResponseMatchSearch>?, t: Throwable?) {
                nextView.onFailure()
            }

            override fun onResponse(call: Call<ResponseMatchSearch>?, response: Response<ResponseMatchSearch>?) {
                response?.body()?.events?.let { nextView.onSuccess(it) }
            }

        })
    }
}