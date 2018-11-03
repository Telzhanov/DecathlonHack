package com.example.askhat.decathlon.events

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
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
            holder.itemView.participate.setOnClickListener {
                var builder = AlertDialog.Builder(context)
                builder.setTitle("Регистрация на событие")
                var view: View = LayoutInflater.from(context).inflate(R.layout.sign_up_event,null)
                var spinner: Spinner = view.findViewById(R.id.spinner1)
                var distances = ArrayList<String>()
                distances.add("3км")
                distances.add("11км")
                distances.add("21км")
                var adapter:ArrayAdapter<String> = ArrayAdapter(context,android.R.layout.simple_dropdown_item_1line,distances)
                spinner.adapter = adapter
                builder.setView(view)
                    .setPositiveButton("Принять участие",object:DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {

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
    }


    class EventViewHolder(view: View):RecyclerView.ViewHolder(view)

}