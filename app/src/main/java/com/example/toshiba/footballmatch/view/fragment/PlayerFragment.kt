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
import com.example.toshiba.footballmatch.adapter.PlayerAdapter
import com.example.toshiba.footballmatch.model.PlayerItem
import com.example.toshiba.footballmatch.model.TeamsItem
import com.example.toshiba.footballmatch.presenter.PlayerPresenter
import com.example.toshiba.footballmatch.presenter.PlayerView

class PlayerFragment : Fragment(), PlayerView {

    override fun onSucces(player: List<PlayerItem>) {
        this.player?.clear()
        this.player?.addAll(player)
        playerAdapter?.notifyDataSetChanged()
    }

    override fun onFailure() {
        if (context != null) {
            Toast.makeText(context, context?.getString(R.string.failure), Toast.LENGTH_LONG).show()
        }
    }

    private var player: MutableList<PlayerItem>? = ArrayList()
    private var playerAdapter: PlayerAdapter? = null
    private var rvItemList: RecyclerView? = null
    private var team: TeamsItem? = null
    private var presenter: PlayerPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        team = arguments?.getParcelable("data")
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        rvItemList = view?.findViewById(R.id.rv_next)

        rvItemList?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        playerAdapter = player?.let { PlayerAdapter(it) }
        rvItemList?.adapter = playerAdapter
        rvItemList?.isNestedScrollingEnabled = false

        presenter = PlayerPresenter(this)
        team?.idTeam?.let { presenter?.getPlayer(it) }
    }
}
