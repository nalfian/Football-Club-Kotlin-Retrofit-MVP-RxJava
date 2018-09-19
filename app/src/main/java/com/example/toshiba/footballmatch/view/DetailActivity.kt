package com.example.toshiba.footballmatch.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.model.EventsItem
import com.example.toshiba.footballmatch.model.TeamsItem
import com.example.toshiba.footballmatch.presenter.DetailPresenter
import com.example.toshiba.footballmatch.presenter.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {
    override fun onSuccesHome(teamsItem: TeamsItem) {
        Picasso.get()
                .load(teamsItem.strTeamBadge)
                .into(ivHome)
        eventsItem?.idAwayTeam?.let { detailPresenter?.getClub(it, false) }
    }

    override fun onSuccesAway(teamsItem: TeamsItem) {
        Picasso.get()
                .load(teamsItem.strTeamBadge)
                .into(ivAway)
    }

    override fun onFailure() {
        Toast.makeText(this, "Cek Internet Anda", Toast.LENGTH_LONG).show()
    }

    var eventsItem: EventsItem? = null
    var detailPresenter: DetailPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Detail Match"
        eventsItem = intent.getParcelableExtra("data")
        detailPresenter = DetailPresenter(this)
        eventsItem?.idHomeTeam?.let { detailPresenter?.getClub(it, true) }
        initSet()
    }

    private fun initSet() {
        tvDateDetail.text = eventsItem?.dateEvent
        tvScoreHomeDet.text = eventsItem?.intHomeScore
        tvScoreAwayDet.text = eventsItem?.intAwayScore
        tvForHome.text = eventsItem?.strHomeFormation
        tvForAway.text = eventsItem?.strAwayFormation
        tvGoalHome.text = eventsItem?.strHomeGoalDetails?.replace("; ", "\n")
        tvGoalAway.text = eventsItem?.strAwayGoalDetails?.replace("; ", "\n")
        tvShotsHome.text = eventsItem?.intHomeShots
        tvShotsAway.text = eventsItem?.intAwayShots
        tvGoalHome.text = eventsItem?.strHomeLineupGoalkeeper?.replace("; ", "\n")
        tvGoalAway.text = eventsItem?.strAwayLineupGoalkeeper?.replace("; ", "\n")
        tvDefHome.text = eventsItem?.strHomeLineupDefense?.replace("; ", "\n")
        tvDefAway.text = eventsItem?.strAwayLineupDefense?.replace("; ", "\n")
        tvMidHome.text = eventsItem?.strHomeLineupMidfield?.replace("; ", "\n")
        tvMidAway.text = eventsItem?.strAwayLineupMidfield?.replace("; ", "\n")
        tvForHome.text = eventsItem?.strHomeLineupForward?.replace("; ", "\n")
        tvForAway.text = eventsItem?.strAwayLineupForward?.replace("; ", "\n")
        tvSubHome.text = eventsItem?.strHomeLineupSubstitutes?.replace("; ", "\n")
        tvSubAway.text = eventsItem?.strAwayLineupSubstitutes?.replace("; ", "\n")
    }

    override fun getSupportParentActivityIntent(): Intent? {
        onBackPressed()
        return null
    }
}
