package com.example.askhat.decathlon.clubs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.Club
import com.example.askhat.decathlon.store.ClubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.clubs_list_fragment.*
import org.koin.android.ext.android.inject
const val EXTRA_CLUB_DETAILS = "EXTRA_CLUB_DETAILS"
class ClubsFragment: Fragment(), ClubsListAdapter.ClubItemClicked {

    private val service: ClubService by inject()

    override fun onClubClicked(club: Club) {
        Logger.msg("accepted", club)
        val intent = Intent(activity, ClubsDetailsActivity::class.java)
        intent.putExtra(EXTRA_CLUB_DETAILS,club as Parcelable)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.clubs_list_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getClubs()
    }

    @SuppressLint("CheckResult")
    private fun getClubs(){
        service.getClubs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setAdapter(it as ArrayList<Club>)
                },{
                    Logger.msg("accepted", it.message)
                })
    }

    private fun setAdapter(club: ArrayList<Club>){
        clubsList.adapter = ClubsListAdapter(activity!!, club, this)
        clubsList.layoutManager = GridLayoutManager(context,2)
    }
}