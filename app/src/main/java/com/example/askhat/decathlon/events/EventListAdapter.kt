package com.example.askhat.decathlon.events

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askhat.decathlon.R
import kotlinx.android.synthetic.main.card_events.view.*

class EventListAdapter(var context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_events,parent,false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position%2==0){
            holder.itemView.backgroundCard.setBackgroundResource(R.drawable.marathon)

        }
        else{
            holder.itemView.backgroundCard.setBackgroundResource(R.drawable.thriatlon)

        }
    }


    class EventViewHolder(view: View):RecyclerView.ViewHolder(view)

}