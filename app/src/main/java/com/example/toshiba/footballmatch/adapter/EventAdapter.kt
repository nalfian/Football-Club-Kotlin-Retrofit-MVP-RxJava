package com.example.toshiba.footballmatch.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.model.EventsItem
import com.example.toshiba.footballmatch.view.DetailActivity

class EventAdapter(private val event: MutableList<EventsItem>) : RecyclerView.Adapter<EventAdapter.ItemView>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemView {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        context = parent.context
        return ItemView(v)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.tvDate?.text = event[position].dateEvent
        holder.tvAway?.text = event[position].strAwayTeam
        holder.tvHome?.text = event[position].strHomeTeam
        holder.tvScoreAway?.text = event[position].intAwayScore
        holder.tvScoreHome?.text = event[position].intHomeScore

        holder.itemView.setOnClickListener {
            context?.startActivity(Intent(context, DetailActivity::class.java)
                    .putExtra("data", event[position]))
        }
    }

    override fun getItemCount(): Int {
        return event.size
    }

    inner class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        val tvHome = itemView.findViewById<TextView>(R.id.tvHome)
        val tvScoreHome = itemView.findViewById<TextView>(R.id.tvScoreHome)
        val tvAway = itemView.findViewById<TextView>(R.id.tvAway)
        val tvScoreAway = itemView.findViewById<TextView>(R.id.tvScoreAway)
    }
}