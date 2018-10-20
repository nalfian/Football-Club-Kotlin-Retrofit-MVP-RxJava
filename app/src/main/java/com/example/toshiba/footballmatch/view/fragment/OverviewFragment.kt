package com.example.toshiba.footballmatch.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.model.TeamsItem
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val team = arguments?.getParcelable<TeamsItem>("data")
        tvOverview.text = team?.strDescriptionEN
    }
}
