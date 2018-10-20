package com.example.toshiba.footballmatch.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.model.PlayerItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        val player = intent.getParcelableExtra<PlayerItem>("data")
        supportActionBar?.title = player?.strPlayer
        initSet(player)
    }

    private fun initSet(player: PlayerItem) {
        tvWeight.text = player.strWeight
        tvHeight.text = player.strHeight
        tvDes.text = player.strDescriptionEN
        Picasso.get()
                .load(player.strFanart1)
                .into(ivPlayer)
    }

    override fun getSupportParentActivityIntent(): Intent? {
        onBackPressed()
        return null
    }
}
