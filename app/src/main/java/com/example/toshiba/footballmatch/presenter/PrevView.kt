package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.EventsItem

interface PrevView {
    fun onSuccess(events: List<EventsItem>)
    fun onFailure()
}