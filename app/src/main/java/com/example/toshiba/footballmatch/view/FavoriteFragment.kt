package com.example.toshiba.footballmatch.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.adapter.EventAdapter
import com.example.toshiba.footballmatch.model.EventsItem
import com.example.toshiba.footballmatch.presenter.FavoritePresenter
import com.example.toshiba.footballmatch.presenter.FavoriteView

class FavoriteFragment : Fragment(), FavoriteView {

    override fun onSuccess(events: List<EventsItem>) {
        this.events?.addAll(events)
        eventAdapter?.notifyDataSetChanged()
    }

    override fun onFailure() {
        Toast.makeText(context, "Cek Internet Anda", Toast.LENGTH_LONG).show()
    }

    private var events: MutableList<EventsItem>? = ArrayList()
    private var eventAdapter: EventAdapter? = null
    private var rvItemList: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fav, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        rvItemList = view?.findViewById(R.id.rv_fav)

        rvItemList?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        eventAdapter = events?.let { EventAdapter(it) }
        rvItemList?.adapter = eventAdapter

        val mainPresenter = FavoritePresenter(this)
        context?.let { mainPresenter.getFav(it) }
        events?.clear()
    }


}
