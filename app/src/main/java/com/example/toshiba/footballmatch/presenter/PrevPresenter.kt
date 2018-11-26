package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.ResponseMatchSearch
import com.example.toshiba.footballmatch.other.AppScheduler
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Response

class PrevPresenter(val prevEvent: PrevView, val schedulers: AppScheduler) {
    private var api: BaseApi? = UtilsApi.apiService
    private val subscription = CompositeDisposable()

    fun getMatch(id: String) {
        subscription.add(api!!.getMatchPrev(id)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe({
                    prevEvent.onSuccess(it.events!!)
                },
                        {
                            prevEvent.onFailure()
                        }
                )
        )
    }

    fun getSearch(name: String) {
        subscription.add(api!!.getSearchMatch(name)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe({
                    prevEvent.onSuccess(it.events!!)
                },
                        {
                            prevEvent.onFailure()
                        }
                )
        )
    }
}