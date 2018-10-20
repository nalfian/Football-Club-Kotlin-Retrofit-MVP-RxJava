package com.example.toshiba.footballmatch.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.adapter.ViewPagerAdapter
import com.example.toshiba.footballmatch.model.Favorite
import com.example.toshiba.footballmatch.model.FavoriteTeam
import com.example.toshiba.footballmatch.model.TeamsItem
import com.example.toshiba.footballmatch.other.database
import com.example.toshiba.footballmatch.view.fragment.OverviewFragment
import com.example.toshiba.footballmatch.view.fragment.PlayerFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamActivity : AppCompatActivity() {

    private var team: TeamsItem? = null
    private var menuItem: Menu? = null
    private var check = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        team = intent.getParcelableExtra("data")

        initToolbar()
        initViewPager()
        initSet()
    }

    private fun initSet() {
        Picasso.get()
                .load(team?.strTeamBadge)
                .into(ivTeam)
        tvStadium.text = team?.strStadium
        tvDate.text = team?.intFormedYear
        tvTeam.text = team?.strTeam
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val bundle = Bundle()
        bundle.putParcelable("data", team)
        val teamFragment = OverviewFragment()
        val playerFragment = PlayerFragment()
        teamFragment.arguments = bundle
        playerFragment.arguments = bundle
        adapter.setFragment(teamFragment, "overview")
        adapter.setFragment(playerFragment, "player")
        view_pager_team.adapter = adapter
        tab_layout_team.setupWithViewPager(view_pager_team)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getSupportParentActivityIntent(): Intent? {
        onBackPressed()
        return null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_detail_team, menu)
        menuItem = menu
        favoriteState()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.favorite) {
            if (check) {
                addToFavorite()
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
                check = false
            } else {
                removeFromFavorite()
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
                check = true
            }
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun addToFavorite() {
        database.use {
            insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to team?.idTeam)
        }
        Toast.makeText(this, getString(R.string.add), Toast.LENGTH_LONG).show()
    }

    private fun removeFromFavorite() {
        database.use {
            delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to team?.idTeam.toString())
        }
        Toast.makeText(this, getString(R.string.remove), Toast.LENGTH_LONG).show()
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to team?.idTeam.toString())
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isEmpty()) {
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this@DetailTeamActivity, R.drawable.ic_star_border_black_24dp)
                check = true
            } else {
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this@DetailTeamActivity, R.drawable.ic_star_black_24dp)
                check = false
            }
        }
    }
}
