package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.EventsItem

interface FavoriteView {
    fun onSuccess(events: List<EventsItem>)
    fun onFailure()
}