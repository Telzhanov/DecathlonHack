package com.example.askhat.decathlon.clubs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askhat.decathlon.R

class ClubsListAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ClubViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_club,parent,false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

    }


    class ClubViewHolder(view: View):RecyclerView.ViewHolder(view)
}