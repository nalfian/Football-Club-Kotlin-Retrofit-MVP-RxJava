package com.example.toshiba.footballmatch.presenter

import com.example.toshiba.footballmatch.model.EventsItem

interface NextView {
    fun onSuccess(events: List<EventsItem>)
    fun onFailure()
}