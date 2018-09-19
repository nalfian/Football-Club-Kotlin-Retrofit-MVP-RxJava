package com.example.toshiba.footballmatch.model

import com.google.gson.annotations.SerializedName

data class ResponseClub(

        @field:SerializedName("teams")
        val teams: List<TeamsItem?>? = null
)