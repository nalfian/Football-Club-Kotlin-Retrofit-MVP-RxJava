package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.ResponseClub
import com.example.toshiba.footballmatch.model.ResponseMatch
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import retrofit2.Call
import retrofit2.Response

class DetailPresenter(val detailView: DetailView){
    private var api: BaseApi? = null

    fun getClub(id: String, home: Boolean){
        api = UtilsApi.apiService
        api?.getClub(id)?.enqueue(object : retrofit2.Callback<ResponseClub>{
            override fun onFailure(call: Call<ResponseClub>?, t: Throwable?) {
                detailView.onFailure()
            }

            override fun onResponse(call: Call<ResponseClub>?, response: Response<ResponseClub>?) {
                if (home){
                    response?.body()?.teams?.get(0)?.let { detailView.onSuccesHome(it) }
                } else{
                    response?.body()?.teams?.get(0)?.let { detailView.onSuccesAway(it) }
                }
            }

        })
    }
}