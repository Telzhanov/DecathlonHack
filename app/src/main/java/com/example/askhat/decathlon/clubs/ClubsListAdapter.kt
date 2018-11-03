package com.example.askhat.decathlon.clubs

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.entities.Club
import kotlinx.android.synthetic.main.card_club.view.*

class ClubsListAdapter(private val context : Context,
                       private val items : ArrayList<Club>,
                       private val listener : ClubItemClicked)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ClubViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_club,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val club = items[p1]
        if (club.photos != "") {
            Glide.with(context)
                    .load(club.photos)
                    .into(p0.itemView.imageClub)
        }
        p0.itemView.nameClub.text = club.title
        p0.itemView.clubDesc.text = club.description

        p0.itemView.infoClub.setOnClickListener{
            listener.onClubClicked(club)
        }
    }


    class ClubViewHolder(view: View):RecyclerView.ViewHolder(view)

    interface ClubItemClicked {
        fun onClubClicked (club : Club)
    }
}