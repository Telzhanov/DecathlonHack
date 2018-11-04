package com.example.askhat.decathlon.history


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.History
import com.example.askhat.decathlon.menu.MainMenuActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.android.ext.android.inject

class HistoryFragment : Fragment(), HistoryAdapter.OnItemClickListener {

    override fun onItemClicked(user: Any) {
        Logger.msg("accepted $user")
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
