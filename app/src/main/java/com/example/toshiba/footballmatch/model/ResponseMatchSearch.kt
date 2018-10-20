package com.example.toshiba.footballmatch.model

import com.google.gson.annotations.SerializedName

data class ResponseMatchSearch(

        @field:SerializedName("event")
        val events: List<EventsItem>? = null
)