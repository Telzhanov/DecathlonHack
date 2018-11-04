package com.example.askhat.decathlon.events

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.menu.MainMenuActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.event_list_fragment.*

class EventsFragment: Fragment(){
    var listEvents = ArrayList<Event>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.event_list_fragment,container,false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainMenuActivity).eventService.getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                listEvents = it as ArrayList<Event>
                progress.visibility = View.GONE
                eventList.adapter = EventListAdapter(context!!,listEvents)
                eventList.layoutManager = LinearLayoutManager(context)

            }


    }
    companion object {
        fun newInstance(service:EventService):EventsFragment{
            var eventsFragment = EventsFragment()
            return eventsFragment
        }
    }
}