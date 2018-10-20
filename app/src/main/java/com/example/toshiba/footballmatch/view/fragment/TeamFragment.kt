package com.example.toshiba.footballmatch.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.example.toshiba.footballmatch.R
import com.example.toshiba.footballmatch.adapter.TeamAdapter
import com.example.toshiba.footballmatch.model.TeamsItem
import com.example.toshiba.footballmatch.other.EspressoTestingIdlingResource
import com.example.toshiba.footballmatch.presenter.TeamPresenter
import com.example.toshiba.footballmatch.presenter.TeamView

class TeamFragment : Fragment(), TeamView {

    override fun onSuccess(team: List<TeamsItem>) {
        this.teams?.clear()
        this.teams?.addAll(team)
        teamAdapter?.notifyDataSetChanged()
    }

    override fun onFailure() {
        if (context != null) {
            Toast.makeText(context, context?.getString(R.string.failure), Toast.LENGTH_LONG).show()
        }
    }

    private var teams: MutableList<TeamsItem>? = ArrayList()
    private var teamAdapter: TeamAdapter? = null
    private var rvItemList: RecyclerView? = null
    private var leagueName: String? = null
    private var presenter: TeamPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        setSpinner(view)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        rvItemList = view?.findViewById(R.id.rv_fav)

        rvItemList?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        teamAdapter = teams?.let { TeamAdapter(it) }
        rvItemList?.adapter = teamAdapter

        presenter = TeamPresenter(this)
        presenter?.getTeam("4328")
        teams?.clear()
    }

    private fun setSpinner(view: View) {
        val spinnerItem = resources.getStringArray(R.array.leagueArray)
        val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, spinnerItem)
        val spTeam = view.findViewById<Spinner>(R.id.spTeam)
        spTeam.adapter = spinnerAdapter
        spTeam.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                EspressoTestingIdlingResource.increment()
                leagueName = spTeam.selectedItem.toString()
                when (leagueName) {
                    "English Premier League" -> presenter?.getTeam("4328")
                    "German Bundesliga" -> presenter?.getTeam("4331")
                    "Italian Serie A" -> presenter?.getTeam("4332")
                    "French Ligue 1" -> presenter?.getTeam("4334")
                    "Spanish La Liga" -> presenter?.getTeam("4335")
                    "Netherlands Eredivisie" -> presenter?.getTeam("4337")
                    else -> presenter?.getTeam("4328")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search_team, menu)
        val searchView = menu?.findItem(R.id.search_team)?.actionView as SearchView
        searchView.isIconfiedByDefault
        searchView.queryHint = resources.getString(R.string.search_team)
        searchView.isFocusable = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
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
