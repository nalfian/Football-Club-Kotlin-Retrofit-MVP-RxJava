package com.example.toshiba.footballmatch.unit

import com.example.toshiba.footballmatch.model.ResponseMatch
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import com.example.toshiba.footballmatch.presenter.PrevPresenter
import com.example.toshiba.footballmatch.presenter.PrevView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

/**
 * Created by root on 2/28/18.
 */
class MatchPresenterTest {
    @Mock
    private
    lateinit var view: PrevView

    @Mock
    private
    lateinit var api: BaseApi

    private lateinit var presenter: PrevPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PrevPresenter(view)
        api = UtilsApi.apiService!!
    }

    @Test
    fun testGetMatchList() {

        api.getMatchPrev().enqueue(object : retrofit2.Callback<ResponseMatch> {
            override fun onFailure(call: Call<ResponseMatch>?, t: Throwable?) {
                Mockito.verify(view).onFailure()
            }

            override fun onResponse(call: Call<ResponseMatch>?, response: Response<ResponseMatch>?) {
                response?.body()?.events?.let {
                    Mockito.verify(view).onSuccess(it)
                }
            }

        })

        presenter.getMatch()
    }
}