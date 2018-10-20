package com.example.toshiba.footballmatch.view.fragment


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
import com.example.toshiba.footballmatch.adapter.TeamAdapter
import com.example.toshiba.footballmatch.model.TeamsItem
import com.example.toshiba.footballmatch.presenter.FavoriteTeamPresenter
import com.example.toshiba.footballmatch.presenter.FavoriteTeamView

class FavoriteTeamFragment : Fragment(), FavoriteTeamView {

    override fun onSuccess(team: List<TeamsItem>) {
        this.teams?.addAll(team)
        teamAdapter?.notifyDataSetChanged()
    }

    override fun onFailure() {
        Toast.makeText(context, context?.getString(R.string.failure), Toast.LENGTH_LONG).show()
    }

    private var teams: MutableList<TeamsItem>? = ArrayList()
    private var teamAdapter: TeamAdapter? = null
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
        teamAdapter = teams?.let { TeamAdapter(it) }
        rvItemList?.adapter = teamAdapter

        val mainPresenter = FavoriteTeamPresenter(this)
        context?.let { mainPresenter.getTeam(it) }
        teams?.clear()
    }


}
