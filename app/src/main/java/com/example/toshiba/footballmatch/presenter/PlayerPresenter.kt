package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.ResponsePlayer
import com.example.toshiba.footballmatch.other.AppScheduler
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Response

class PlayerPresenter(val playerView: PlayerView, val schedulers: AppScheduler) {
    private var api: BaseApi? = UtilsApi.apiService
    private val subscription = CompositeDisposable()

    fun getPlayer(id: String) {
        subscription.add(api!!.getAllPlayers(id)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe({
                    playerView.onSucces(it?.player!!)
                },
                        {
                            playerView.onFailure()
                        }
                )
        )
    }
}