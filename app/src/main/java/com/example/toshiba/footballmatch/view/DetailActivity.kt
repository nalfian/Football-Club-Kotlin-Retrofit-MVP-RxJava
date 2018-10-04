package com.example.toshiba.footballmatch.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.model.EventsItem
import com.example.toshiba.footballmatch.model.Favorite
import com.example.toshiba.footballmatch.model.TeamsItem
import com.example.toshiba.footballmatch.other.database
import com.example.toshiba.footballmatch.presenter.DetailPresenter
import com.example.toshiba.footballmatch.presenter.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

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

    private var eventsItem: EventsItem? = null
    private var detailPresenter: DetailPresenter? = null
    private var check = true
    private var menuItem: Menu? = null


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
        tvAway.text = eventsItem?.strAwayTeam
        tvHome.text = eventsItem?.strHomeTeam
        tvDateDetail.text = eventsItem?.dateEvent
        tvScoreHomeDet.text = eventsItem?.intHomeScore
        tvScoreAwayDet.text = eventsItem?.intAwayScore
        tvFormHome.text = eventsItem?.strHomeFormation
        tvFormAway.text = eventsItem?.strAwayFormation
        tvGoalHome.text = eventsItem?.strHomeGoalDetails?.replace(";", "\n")
        tvGoalAway.text = eventsItem?.strAwayGoalDetails?.replace(";", "\n")
        tvShotsHome.text = eventsItem?.intHomeShots
        tvShotsAway.text = eventsItem?.intAwayShots
        tvKeepHome.text = eventsItem?.strHomeLineupGoalkeeper?.replace("; ", "\n")
        tvKeepAway.text = eventsItem?.strAwayLineupGoalkeeper?.replace("; ", "\n")
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_detail, menu)
        menuItem = menu
        favoriteState()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.favorite) {
            if (check) {
                addToFavorite()
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
                check = false
            } else {
                removeFromFavorite()
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
                check = true
            }
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun addToFavorite() {
        database.use {
            insert(Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to eventsItem?.idEvent)
        }
        Toast.makeText(this, "Add to Favorite", Toast.LENGTH_LONG).show()
    }

    private fun removeFromFavorite() {
        database.use {
            delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to eventsItem?.idEvent.toString())
        }
        Toast.makeText(this, "Remove from Favorite", Toast.LENGTH_LONG).show()
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to eventsItem?.idEvent.toString())
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isEmpty()) {
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_favorite_border_black_24dp)
                check = true
            } else {
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_favorite_black_24dp)
                check = false
            }
        }
    }
}
