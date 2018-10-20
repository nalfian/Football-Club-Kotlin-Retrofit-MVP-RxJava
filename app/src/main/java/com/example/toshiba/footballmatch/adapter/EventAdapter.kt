package com.example.toshiba.footballmatch.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.model.EventsItem
import com.example.toshiba.footballmatch.other.CalendarHelper
import com.example.toshiba.footballmatch.view.activity.DetailActivity
import java.text.SimpleDateFormat

class EventAdapter(private val event: MutableList<EventsItem>, private val next: Boolean, val activity: FragmentActivity?) : RecyclerView.Adapter<EventAdapter.ItemView>() {

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
        holder.tvTime?.text = event[position].strTime
        holder.tvScoreAway?.text = event[position].intAwayScore
        holder.tvScoreHome?.text = event[position].intHomeScore

        if (!next) {
            holder.ivNotif?.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            context?.startActivity(Intent(context, DetailActivity::class.java)
                    .putExtra("data", event[position]))
        }

        holder.ivNotif?.setOnClickListener {
            if (CalendarHelper.haveCalendarReadWritePermissions(activity)) {
                addEventToGoogleCalendar(event[position])
            } else {
                CalendarHelper.requestCalendarReadWritePermission(activity)
            }
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
        val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
        val ivNotif = itemView.findViewById<ImageView>(R.id.ivNotif)
    }


    @SuppressLint("MissingPermission", "Recycle")
    private fun getGoogleCalendarId(): Long {

        val event =
                arrayOf(CalendarContract.Calendars._ID,
                        CalendarContract.Calendars.NAME,
                        CalendarContract.Calendars.ACCOUNT_NAME,
                        CalendarContract.Calendars.ACCOUNT_TYPE)

        if (CalendarHelper.haveCalendarReadWritePermissions(activity)) {
            val cursor = context?.contentResolver
                    ?.query(CalendarContract.Calendars.CONTENT_URI, event,
                            CalendarContract.Calendars.VISIBLE + " = 1",
                            null,
                            CalendarContract.Calendars._ID + " ASC")

            if (cursor!!.moveToFirst()) {
                do {
                    val id = cursor.getLong(0)
                    return id

                } while (cursor.moveToNext())
            }
        }
        return -1

    }

    @SuppressLint("SimpleDateFormat")
    private fun addEventToGoogleCalendar(model: EventsItem) {
        val calId = getGoogleCalendarId()
        if (calId == -1L) {
            Toast.makeText(context, "Somethings went wrong, try again!",
                    Toast.LENGTH_SHORT).show()
            return
        }
        val title = model.strEvent
        val times = model.strTime?.split("+")?.get(0)
        val date = model.dateEvent
        val dateTimePick = "$date $times"
        val simpleDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val newDate = simpleDate.parse(dateTimePick)
        val timeInMillis = newDate.time
        val end = timeInMillis + 5400000
        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra(CalendarContract.Events.TITLE, title)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeInMillis)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Television")
        intent.putExtra(CalendarContract.Events.DESCRIPTION, title)
        context?.startActivity(intent)
    }
}