package com.example.toshiba.footballmatch.model

import com.google.gson.annotations.SerializedName

data class ResponseTeam(

        @field:SerializedName("teams")
        val teams: List<TeamsItem>? = null
)