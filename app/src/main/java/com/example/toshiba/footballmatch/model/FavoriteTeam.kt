package com.example.toshiba.footballmatch.model

data class FavoriteTeam(val id: Long?,
                        val teamId: String?) {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
    }
}