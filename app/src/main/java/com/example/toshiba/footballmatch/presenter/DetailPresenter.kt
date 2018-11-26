package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.ResponseTeam
import com.example.toshiba.footballmatch.other.AppScheduler
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Response

class DetailPresenter(val detailView: DetailView, val schedulers: AppScheduler) {
    private var api: BaseApi? = null
    private val subscription = CompositeDisposable()

    fun getClub(id: String, home: Boolean) {
        api = UtilsApi.apiService
        subscription.add(api!!.getClub(id)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe({
                    if (home) {
                        detailView.onSuccesHome(it.teams!![0])
                    } else {
                        detailView.onSuccesAway(it.teams!![0])
                    }
                },
                        {
                            detailView.onFailure()
                        }
                )
        )
    }
}