package com.example.askhat.decathlon.events

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.entities.Event
import com.example.askhat.decathlon.menu.MainMenuActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.card_events.view.*

class EventListAdapter(var context: Context,var events:ArrayList<Event>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_events,parent,false))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.time.text = events[position].date
        holder.itemView.nameMarathon.text = events[position].title
        if (events[position].typeOf.equals("Марафон")){
            holder.itemView.backgroundCard.setBackgroundResource(R.drawable.marathon)
        }
        else{
            holder.itemView.backgroundCard.setBackgroundResource(R.drawable.thriatlon)
        }
        holder.itemView.participate.setOnClickListener {
            var builder = AlertDialog.Builder(context)
            builder.setTitle("Регистрация на событие")
            var view: View = LayoutInflater.from(context).inflate(R.layout.sign_up_event,null)
            var spinner: Spinner = view.findViewById(R.id.spinner1)
            var title: TextView = view.findViewById(R.id.titleEvent)
            var desc :TextView = view.findViewById(R.id.infoEvent)
            var price:TextView = view.findViewById(R.id.priceEvent)
            var bonus :TextView = view.findViewById(R.id.bonusTextView)
            title.text = events[position].title
            desc.text = events[position].description
            bonus.text = events[position].docoins.toString()
            var distances = ArrayList<String>()
            distances.add("3км")
            distances.add("11км")
            distances.add("21км")

            var adapter:ArrayAdapter<String> = ArrayAdapter(context,android.R.layout.simple_dropdown_item_1line,distances)
            spinner.adapter = adapter
            builder.setView(view)
                .setPositiveButton("Принять участие",object:DialogInterface.OnClickListener{
                    @SuppressLint("CheckResult")
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        (context as MainMenuActivity).eventService.subscribe(MainMenuActivity.user!!.id, events[position].id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                (context as MainMenuActivity).updatePoints(it.coins)
                            }
                    }
                })
                .setNegativeButton("Отмена", object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                    }

                })
                .create()
                .show()
        }
    }


    class EventViewHolder(view: View):RecyclerView.ViewHolder(view)

}