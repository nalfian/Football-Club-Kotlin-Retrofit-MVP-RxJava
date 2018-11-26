package com.example.toshiba.footballmatch.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.adapter.EventAdapter
import com.example.toshiba.footballmatch.model.EventsItem
import com.example.toshiba.footballmatch.other.AppScheduler
import com.example.toshiba.footballmatch.other.EspressoTestingIdlingResource
import com.example.toshiba.footballmatch.presenter.NextPresenter
import com.example.toshiba.footballmatch.presenter.NextView

class NextFragment : Fragment(), NextView {
    override fun onSuccess(events: List<EventsItem>) {
        EspressoTestingIdlingResource.decrement()
        this.events?.clear()
        this.events?.addAll(events)
        eventAdapter?.notifyDataSetChanged()
    }

    override fun onFailure() {
        EspressoTestingIdlingResource.decrement()
        if (context != null) {
            Toast.makeText(context, context?.getString(R.string.failure), Toast.LENGTH_LONG).show()
        }
    }

    private var events: MutableList<EventsItem>? = ArrayList()
    private var eventAdapter: EventAdapter? = null
    private var rvItemList: RecyclerView? = null
    private var presenter: NextPresenter? = null
    private var leagueName: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_next, container, false)
        setSpinner(view)
        initView(view)
        return view
    }

    private fun setSpinner(view: View) {
        val spinnerItem = resources.getStringArray(R.array.leagueArray)
        val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, spinnerItem)
        val spMatch = view.findViewById<Spinner>(R.id.spMatch)
        spMatch.adapter = spinnerAdapter
        spMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                EspressoTestingIdlingResource.increment()
                leagueName = spMatch.selectedItem.toString()
                when (leagueName) {
                    "English Premier League" -> presenter?.getMatch("4328")
                    "German Bundesliga" -> presenter?.getMatch("4331")
                    "Italian Serie A" -> presenter?.getMatch("4332")
                    "French Ligue 1" -> presenter?.getMatch("4334")
                    "Spanish La Liga" -> presenter?.getMatch("4335")
                    "Netherlands Eredivisie" -> presenter?.getMatch("4337")
                    else -> presenter?.getMatch("4328")
                }
            }

        }
    }

    private fun initView(view: View?) {
        rvItemList = view?.findViewById(R.id.rv_next)

        rvItemList?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        eventAdapter = events?.let { EventAdapter(it, true, activity) }
        rvItemList?.adapter = eventAdapter
        rvItemList?.isNestedScrollingEnabled = false

        EspressoTestingIdlingResource.increment()
        val schedulers = AppScheduler()
        presenter = NextPresenter(this, schedulers)
        presenter?.getMatch("4328")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.isIconfiedByDefault
        searchView.queryHint = resources.getString(R.string.search_match)
        searchView.isFocusable = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                EspressoTestingIdlingResource.increment()
                query?.let { presenter?.getSearch(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }
}
