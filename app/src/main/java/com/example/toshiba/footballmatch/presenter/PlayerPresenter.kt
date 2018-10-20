package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.ResponsePlayer
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import retrofit2.Call
import retrofit2.Response

class PlayerPresenter(val playerView: PlayerView) {
    private var api: BaseApi? = null
    fun getPlayer(id: String) {
        api = UtilsApi.apiService
        api?.getAllPlayers(id)?.enqueue(object : retrofit2.Callback<ResponsePlayer> {
            override fun onFailure(call: Call<ResponsePlayer>, t: Throwable) {
                playerView.onFailure()
            }

            override fun onResponse(call: Call<ResponsePlayer>, response: Response<ResponsePlayer>) {
                response.body()?.player?.let { playerView.onSucces(it) }
            }
        })
    }
}