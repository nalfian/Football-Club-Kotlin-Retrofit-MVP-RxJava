package com.example.toshiba.footballmatch.other

import com.example.toshiba.footballmatch.BuildConfig

object UtilsApi {
    val apiService: BaseApi
        get() = RetrofitClient.getClient(BuildConfig.BASE_URL)!!.create(BaseApi::class.java)
}