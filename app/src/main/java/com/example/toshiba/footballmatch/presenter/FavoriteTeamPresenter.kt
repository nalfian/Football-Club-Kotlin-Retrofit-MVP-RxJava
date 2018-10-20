package com.example.toshiba.footballmatch.presenter

import android.content.Context
import com.example.toshiba.footballmatch.model.FavoriteTeam
import com.example.toshiba.footballmatch.model.ResponseTeam
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import com.example.toshiba.footballmatch.other.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import retrofit2.Call
import retrofit2.Response

class FavoriteTeamPresenter(val teamView: FavoriteTeamView) {

    fun getTeam(context: Context) {
        val api: BaseApi? = UtilsApi.apiService
        val favorites: MutableList<FavoriteTeam> = mutableListOf()

        context.database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
        }

        for (i in 0 until favorites.size) {
            favorites[i].teamId?.let {
                api?.getClub(it)?.enqueue(object : retrofit2.Callback<ResponseTeam> {
                    override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                        teamView.onFailure()
                    }

                    override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                        response.body()?.teams?.let { it1 -> teamView.onSuccess(it1) }
                    }

                })
            }
        }
    }
}