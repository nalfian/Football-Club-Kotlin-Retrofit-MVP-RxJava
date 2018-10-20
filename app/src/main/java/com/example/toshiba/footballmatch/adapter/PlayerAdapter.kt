package com.example.toshiba.footballmatch.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.model.PlayerItem
import com.squareup.picasso.Picasso

class PlayerAdapter(private val player: MutableList<PlayerItem>) : RecyclerView.Adapter<PlayerAdapter.ItemView>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemView {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        context = parent.context
        return ItemView(v)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.tvPlayer.text = player[position].strPlayer
        holder.tvPosition.text = player[position].strPosition
        Picasso.get()
                .load(player[position].strThumb)
                .into(holder.ivPlayer)
    }

    override fun getItemCount(): Int {
        return player.size
    }

    inner class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPlayer = itemView.findViewById<TextView>(R.id.tvPlayer)
        val ivPlayer = itemView.findViewById<ImageView>(R.id.ivPlayer)
        val tvPosition = itemView.findViewById<TextView>(R.id.tvPositon)
    }
}