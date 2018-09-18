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
import com.example.toshiba.footballmatch.presenter.NextPresenter
import com.example.toshiba.footballmatch.presenter.NextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NextFragment : Fragment(), NextView {
    override fun onSuccess(events: List<EventsItem>) {
        this.events?.clear()
        this.events?.addAll(events)
        eventAdapter?.notifyDataSetChanged()
    }

    override fun onFailure() {
        Toast.makeText(context, "Cek Internet Anda", Toast.LENGTH_LONG).show()
    }

    var events: MutableList<EventsItem>? = ArrayList()
    var eventAdapter: EventAdapter? = null
    var rvItemList: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_next, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        rvItemList = view?.findViewById(R.id.rv)

        rvItemList?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        eventAdapter = events?.let { EventAdapter(it) }
        rvItemList?.adapter = eventAdapter

        val mainPresenter = NextPresenter(this)
        mainPresenter.getMatch()
    }
}
