package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.TeamsItem

interface FavoriteTeamView {
    fun onSuccess(team: List<TeamsItem>)
    fun onFailure()
}