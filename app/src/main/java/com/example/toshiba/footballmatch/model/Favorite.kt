package com.example.toshiba.footballmatch.model

data class Favorite(val id: Long?,
                    val eventId: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
    }
}