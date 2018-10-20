package com.example.toshiba.footballmatch.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.model.TeamsItem
import com.example.toshiba.footballmatch.view.activity.DetailTeamActivity
import com.squareup.picasso.Picasso

class TeamAdapter(private val team: MutableList<TeamsItem>) : RecyclerView.Adapter<TeamAdapter.ItemView>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemView {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        context = parent.context
        return ItemView(v)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.tvTeam.text = team[position].strTeam
        Picasso.get()
                .load(team[position].strTeamBadge)
                .into(holder.ivTeam)
        holder.itemView.setOnClickListener {
            context?.startActivity(Intent(context, DetailTeamActivity::class.java)
                    .putExtra("data", team[position]))
        }
    }

    override fun getItemCount(): Int {
        return team.size
    }

    inner class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTeam = itemView.findViewById<TextView>(R.id.tvTeam)
        val ivTeam = itemView.findViewById<ImageView>(R.id.ivTeam)
    }
}