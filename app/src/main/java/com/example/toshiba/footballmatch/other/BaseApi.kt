package com.example.toshiba.footballmatch.other

import com.example.toshiba.footballmatch.BuildConfig
import com.example.toshiba.footballmatch.model.ResponseMatch
import com.example.toshiba.footballmatch.model.ResponseMatchSearch
import com.example.toshiba.footballmatch.model.ResponsePlayer
import com.example.toshiba.footballmatch.model.ResponseTeam
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {

    @GET(BuildConfig.EVENT_LAST)
    fun getMatchPrev(@Query("id") id: String): Observable<ResponseMatch>

    @GET(BuildConfig.EVENT_NEXT)
    fun getMatchNext(@Query("id") id: String): Observable<ResponseMatch>

    @GET(BuildConfig.CLUB)
    fun getClub(@Query("id") id: String): Observable<ResponseTeam>

    @GET(BuildConfig.EVENT_ID)
    fun getEvent(@Query("id") id: String): Observable<ResponseMatch>

    @GET(BuildConfig.TEAM_SEARCH)
    fun getSearchTeam(@Query("t") query: String?): Observable<ResponseTeam>

    @GET(BuildConfig.EVENT_SEARCH)
    fun getSearchMatch(@Query("e") query: String?): Observable<ResponseMatchSearch>

    @GET(BuildConfig.TEAM_ALL)
    fun getAllTeam(@Query("id") id: String): Observable<ResponseTeam>

    @GET(BuildConfig.PLAYER_ID)
    fun getAllPlayers(@Query("id") id: String?): Observable<ResponsePlayer>

}