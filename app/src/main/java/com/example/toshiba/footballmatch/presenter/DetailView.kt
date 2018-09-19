package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.TeamsItem

interface DetailView{
    fun onSuccesHome(teamsItem: TeamsItem)
    fun onSuccesAway(teamsItem: TeamsItem)
    fun onFailure()
}