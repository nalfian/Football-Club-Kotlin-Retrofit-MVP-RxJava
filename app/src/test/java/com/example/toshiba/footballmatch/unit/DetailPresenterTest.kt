package com.example.toshiba.footballmatch.unit

import com.example.toshiba.footballmatch.model.ResponseClub
import com.example.toshiba.footballmatch.other.BaseApi
import com.example.toshiba.footballmatch.other.UtilsApi
import com.example.toshiba.footballmatch.presenter.DetailPresenter
import com.example.toshiba.footballmatch.presenter.DetailView
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
class DetailPresenterTest {
    @Mock
    private
    lateinit var view: DetailView

    @Mock
    private
    lateinit var api: BaseApi

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view)
        api = UtilsApi.apiService!!
    }

    @Test
    fun testGetTeam() {

        api.getClub("1234").enqueue(object : retrofit2.Callback<ResponseClub> {
            override fun onFailure(call: Call<ResponseClub>?, t: Throwable?) {
                Mockito.verify(view).onFailure()
            }

            override fun onResponse(call: Call<ResponseClub>?, response: Response<ResponseClub>?) {
                response?.body()?.teams?.get(0)?.let {
                    Mockito.verify(view).onSuccesHome(it)
                }
            }

        })

        presenter.getClub("1234", true)

    }
}