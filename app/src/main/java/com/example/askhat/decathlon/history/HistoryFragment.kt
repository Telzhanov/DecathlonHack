package com.example.askhat.decathlon.history


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
import com.example.askhat.decathlon.clubs.ClubsDetailsActivity
import com.example.askhat.decathlon.clubs.EXTRA_CLUB_DETAILS
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.Club
import com.example.askhat.decathlon.entities.Event
import com.example.askhat.decathlon.entities.History
import com.example.askhat.decathlon.entities.Product
import com.example.askhat.decathlon.menu.MainMenuActivity
import com.example.askhat.decathlon.store.EXTRA_DETAILS
import com.example.askhat.decathlon.store.ProductDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.android.ext.android.inject

class HistoryFragment : Fragment(), HistoryAdapter.OnItemClickListener {

    override fun onItemClicked(obj: Any) {
        when(obj){
            is Club -> {
                Logger.msg("accepted", obj)
                val intent = Intent(activity, ClubsDetailsActivity::class.java)
                intent.putExtra(EXTRA_CLUB_DETAILS,obj as Parcelable)
                startActivity(intent)
            }
            is Product -> {
                Logger.msg("accepted", obj)
                val intent = Intent(activity, ProductDetailsActivity::class.java)
                intent.putExtra(EXTRA_DETAILS,obj as Parcelable)
                startActivity(intent)
            }
            is Event -> {

            }
        }
    }

    private val service: HistoryService by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getHistory()
    }

    @SuppressLint("CheckResult")
    private fun getHistory() {
        service.getHistory(MainMenuActivity.user!!.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Logger.msg("accepted", it.toString())
                            val items = it as History
                            val list = arrayListOf<Any>()
                            list.addAll(items.clubs)
                            list.addAll(items.products)
                            list.addAll(items.events)
                            setProducts(list)
                        },
                        {
                            Logger.msg("accepted --- ${it.message}")
                        })
    }

    private fun setProducts(list: ArrayList<Any>) {
        historyList.adapter = HistoryAdapter(activity!!,list, this)
        historyList.layoutManager = GridLayoutManager(context,1)
    }


}
