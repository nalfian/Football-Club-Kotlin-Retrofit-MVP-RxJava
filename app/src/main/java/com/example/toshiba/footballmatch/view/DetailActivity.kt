package com.example.toshiba.footballmatch.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.model.EventsItem

class DetailActivity : AppCompatActivity() {

    var eventsItem: EventsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Detail Match"
        eventsItem = intent.getParcelableExtra("data")
        initView()
        initSet()
    }

    private fun initView() {

    }

    private fun initSet() {

    }
}
