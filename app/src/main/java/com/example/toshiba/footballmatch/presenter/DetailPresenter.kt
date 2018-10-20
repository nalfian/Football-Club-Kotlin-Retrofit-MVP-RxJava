package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.ResponseTeam
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import retrofit2.Call
import retrofit2.Response

class DetailPresenter(val detailView: DetailView){
    private var api: BaseApi? = null

    fun getClub(id: String, home: Boolean){
        api = UtilsApi.apiService
        api?.getClub(id)?.enqueue(object : retrofit2.Callback<ResponseTeam>{
            override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                detailView.onFailure()
            }

            override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                if (home){
                    response?.body()?.teams?.get(0)?.let { detailView.onSuccesHome(it) }
                } else{
                    response?.body()?.teams?.get(0)?.let { detailView.onSuccesAway(it) }
                }
            }

        })
    }
}