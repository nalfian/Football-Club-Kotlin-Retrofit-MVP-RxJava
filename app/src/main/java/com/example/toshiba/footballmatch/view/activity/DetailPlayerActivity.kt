package com.example.toshiba.footballmatch.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.toshiba.footballmatch.R

class DetailPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
    }

    override fun getSupportParentActivityIntent(): Intent? {
        onBackPressed()
        return null
    }
}
