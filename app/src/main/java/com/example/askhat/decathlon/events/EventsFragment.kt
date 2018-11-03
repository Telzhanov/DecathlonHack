package com.example.askhat.decathlon.events

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askhat.decathlon.R
import kotlinx.android.synthetic.main.event_list_fragment.*

class EventsFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.event_list_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventList.adapter = EventListAdapter(context!!)
        eventList.layoutManager = LinearLayoutManager(context)

    }
}