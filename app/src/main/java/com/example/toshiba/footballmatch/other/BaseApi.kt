package com.example.toshiba.footballmatch.other

import com.example.toshiba.footballmatch.BuildConfig
import com.example.toshiba.footballmatch.model.ResponseMatch
import retrofit2.Call
import retrofit2.http.GET

interface BaseApi {
    @GET(BuildConfig.EVENT_LAST)
    fun getMatchPrev(): Call<ResponseMatch>

    @GET(BuildConfig.EVENT_NEXT)
    fun getMatchNext(): Call<ResponseMatch>
}