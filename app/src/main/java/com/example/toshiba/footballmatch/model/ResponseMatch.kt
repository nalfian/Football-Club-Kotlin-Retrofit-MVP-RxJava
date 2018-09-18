package com.example.toshiba.footballmatch.model

import com.google.gson.annotations.SerializedName

data class ResponseMatch(

        @field:SerializedName("events")
        val events: List<EventsItem>? = null
)