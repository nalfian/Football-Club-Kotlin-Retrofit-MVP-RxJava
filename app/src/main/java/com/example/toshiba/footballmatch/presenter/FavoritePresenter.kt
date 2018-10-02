package com.example.toshiba.footballmatch.presenter

import android.content.Context
import com.example.toshiba.footballmatch.model.Favorite
import com.example.toshiba.footballmatch.model.ResponseMatch
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import com.example.toshiba.footballmatch.other.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import retrofit2.Call
import retrofit2.Response

class FavoritePresenter(val favoriteView: FavoriteView) {

    fun getFav(context: Context) {
        val api: BaseApi = UtilsApi.apiService
        val favorites: MutableList<Favorite> = mutableListOf()

        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
        }

        for (i in 0 until favorites.size) {
            favorites[i].eventId?.let {
                api.getEvent(it).enqueue(object : retrofit2.Callback<ResponseMatch> {
                    override fun onFailure(call: Call<ResponseMatch>?, t: Throwable?) {
                        favoriteView.onFailure()
                    }

                    override fun onResponse(call: Call<ResponseMatch>?, response: Response<ResponseMatch>?) {
                        response?.body()?.events?.let {
                            favoriteView.onSuccess(it) }
                    }

                })
            }
        }
    }
}