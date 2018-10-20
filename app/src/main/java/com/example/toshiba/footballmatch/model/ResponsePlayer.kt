package com.example.toshiba.footballmatch.model

import com.google.gson.annotations.SerializedName

data class ResponsePlayer(

        @field:SerializedName("player")
        val player: MutableList<PlayerItem>? = null
)