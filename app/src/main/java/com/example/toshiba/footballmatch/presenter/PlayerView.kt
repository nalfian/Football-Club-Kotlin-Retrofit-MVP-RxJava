package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.PlayerItem

interface PlayerView {
    fun onSucces(player: List<PlayerItem>)
    fun onFailure()
}