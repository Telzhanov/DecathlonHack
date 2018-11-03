package com.example.askhat.decathlon.clubs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.events.EventListAdapter
import kotlinx.android.synthetic.main.clubs_list_fragment.*
import kotlinx.android.synthetic.main.event_list_fragment.*

class ClubsFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.clubs_list_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clubsList.adapter = ClubsListAdapter()
        clubsList.layoutManager = GridLayoutManager(context,2)

    }
}