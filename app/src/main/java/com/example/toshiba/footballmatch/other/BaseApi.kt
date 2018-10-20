package com.example.toshiba.footballmatch.other

import com.example.toshiba.footballmatch.BuildConfig
import com.example.toshiba.footballmatch.model.ResponseTeam
import com.example.toshiba.footballmatch.model.ResponseMatch
import com.example.toshiba.footballmatch.model.ResponsePlayer
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {
    @GET(BuildConfig.EVENT_LAST)
    fun getMatchPrev(@Query("id") id: String): Call<ResponseMatch>

    @GET(BuildConfig.EVENT_NEXT)
    fun getMatchNext(@Query("id") id: String): Call<ResponseMatch>

    @GET(BuildConfig.CLUB)
    fun getClub(@Query("id") id: String): Call<ResponseTeam>

    @GET(BuildConfig.EVENT_ID)
    fun getEvent(@Query("id") id: String): Call<ResponseMatch>

    @GET(BuildConfig.TEAM_SEARCH)
    fun getSearchTeam(@Query("name") query: String?): Call<ResponseTeam>

    @GET(BuildConfig.EVENT_SEARCH)
    fun getSearchMatch(@Query("name") query: String?): Call<ResponseMatch>

    @GET(BuildConfig.TEAM_ALL)
    fun getAllTeam(@Query("id") id: String): Call<ResponseTeam>

    @GET(BuildConfig.PLAYER_ID)
    fun getAllPlayers(@Query("id") id: String?): Call<ResponsePlayer>

}