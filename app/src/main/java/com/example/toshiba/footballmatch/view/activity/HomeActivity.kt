package com.example.toshiba.footballmatch.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.view.fragment.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_next -> {
                val fragment = NextFragment()
                openFragment(fragment)
                supportActionBar?.title = getString(R.string.next)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_prev -> {
                val fragment = PrevFragment()
                openFragment(fragment)
                supportActionBar?.title = getString(R.string.prev)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorite -> {
                val fragment = FavoriteFragment()
                openFragment(fragment)
                supportActionBar?.title = getString(R.string.fav_matc)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_team -> {
                val fragment = TeamFragment()
                openFragment(fragment)
                supportActionBar?.title = getString(R.string.teamm)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorite_team -> {
                val fragment = FavoriteTeamFragment()
                openFragment(fragment)
                supportActionBar?.title = getString(R.string.fav_team)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = PrevFragment()
        openFragment(fragment)
        supportActionBar?.title = getString(R.string.prev)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }
}
