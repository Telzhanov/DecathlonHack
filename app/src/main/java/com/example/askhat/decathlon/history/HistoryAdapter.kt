package com.example.askhat.decathlon.history

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.Club
import com.example.askhat.decathlon.entities.Event
import com.example.askhat.decathlon.entities.Product
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val context: Context,
                     private var dataset: ArrayList<Any>,
                     private val listener: HistoryFragment)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    object HolderTypes {
        const val CLUB = 0
        const val PRODUCT = 1
        const val EVENT = 2
    }

    override fun getItemViewType(position: Int): Int = when (dataset[position]) {
        is Club -> HolderTypes.CLUB
        is Product -> HolderTypes.PRODUCT
        else -> {
            HolderTypes.EVENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = run {
        when (viewType) {
            HolderTypes.CLUB -> ClubListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_history, parent, false))
            HolderTypes.PRODUCT -> ProductListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_history, parent, false))
            else -> EventListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_history, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ClubListViewHolder -> {
                Logger.msg("accepted", "ClubListViewHolder")
                val obj = dataset[position] as Club
                holder.itemView.historyTitle.text = obj.title
                holder.itemView.historyPrice.text = "Цена: "+obj.price.toString()
                holder.itemView.historyType.text = "Тип: "+obj.javaClass.simpleName
                if (obj.photos != "") {
                    Glide.with(context)
                            .load(obj.photos)
                            .into(holder.itemView.bgIVHistory)
                }

                if(obj.decocoins > 0){
                    holder.itemView.historyBonus.text = "+"+obj.decocoins.toString()
                }else{
                    holder.itemView.historyBonus.text = obj.decocoins.toString()
                    holder.itemView.historyBonus.setTextColor(ContextCompat.getColor(context, R.color.colorLoginPurple))
                }
                holder.itemView.setOnClickListener {
                    listener.onItemClicked(obj)
                }
            }
            is ProductListViewHolder -> {
                Logger.msg("accepted", "ProductListViewHolder")
                val obj = dataset[position] as Product
                holder.itemView.historyTitle.text = obj.title
                holder.itemView.historyPrice.text = "Цена: "+obj.price.toString()
                holder.itemView.historyType.text = "Тип: "+obj.javaClass.simpleName
                if (obj.photos != "") {
                    Glide.with(context)
                            .load(obj.photos)
                            .into(holder.itemView.bgIVHistory)
                }
                if(obj.docoins > 0){
                    holder.itemView.historyBonus.text = "+"+obj.docoins.toString()
                }else{
                    holder.itemView.historyBonus.text = obj.docoins.toString()
                    holder.itemView.historyBonus.setTextColor(ContextCompat.getColor(context, R.color.colorLoginPurple))
                }

                holder.itemView.setOnClickListener {
                    listener.onItemClicked(obj)
                }
            }
            is EventListViewHolder -> {
                Logger.msg("accepted", "EventListViewHolder")
                val obj = dataset[position] as Event
                holder.itemView.historyTitle.text = obj.title
                holder.itemView.historyPrice.text = "Цена: "+obj.price.toString()
                holder.itemView.historyType.text = "Тип: "+obj.javaClass.simpleName
                if(obj.docoins > 0){
                    holder.itemView.historyBonus.text = "+"+obj.docoins.toString()
                }else{
                    holder.itemView.historyBonus.text = obj.docoins.toString()
                    holder.itemView.historyBonus.setTextColor(ContextCompat.getColor(context, R.color.colorLoginPurple))
                }
                if (obj.photos != "") {
                    Glide.with(context)
                            .load(obj.photos)
                            .into(holder.itemView.bgIVHistory)
                }
                holder.itemView.setOnClickListener {
                    listener.onItemClicked(obj)
                }
            }
        }
    }


    inner class ClubListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class EventListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnItemClickListener {
        fun onItemClicked(user: Any)
    }
}