package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.ResponseTeam
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import retrofit2.Call
import retrofit2.Response

class TeamPresenter(val teamView: TeamView) {
    private var api: BaseApi? = null
    fun getTeam(id: String) {
        api = UtilsApi.apiService
        api?.getAllTeam(id)?.enqueue(object : retrofit2.Callback<ResponseTeam> {
            override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                teamView.onFailure()
            }

            override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                response.body()?.teams?.let { teamView.onSuccess(it) }
            }
        })
    }
}