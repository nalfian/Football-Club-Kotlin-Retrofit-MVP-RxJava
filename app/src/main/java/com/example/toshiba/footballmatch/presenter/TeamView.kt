package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.EventsItem
import com.example.toshiba.footballmatch.model.TeamsItem

interface TeamView {
    fun onSuccess(team: List<TeamsItem>)
    fun onFailure()
}