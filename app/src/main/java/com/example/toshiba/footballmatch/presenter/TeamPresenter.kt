package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.ResponseTeam
import com.example.toshiba.footballmatch.other.AppScheduler
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Response

class TeamPresenter(val teamView: TeamView, val schedulers: AppScheduler) {
    private var api: BaseApi? = UtilsApi.apiService
    private val subscription = CompositeDisposable()

    fun getTeam(id: String) {
        subscription.add(api!!.getAllTeam(id)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe({
                    teamView.onSuccess(it.teams!!)
                },
                        {
                            teamView.onFailure()
                        }
                )
        )
    }

    fun getSearch(name: String) {
        subscription.add(api!!.getSearchTeam(name)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe({
                    teamView.onSuccess(it.teams!!)
                },
                        {
                            teamView.onFailure()
                        }
                )
        )
    }
}