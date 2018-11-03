package com.example.askhat.decathlon.basket


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
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.Product
import com.example.askhat.decathlon.store.EXTRA_DETAILS
import com.example.askhat.decathlon.store.ProductDetailsActivity
import com.example.askhat.decathlon.store.StoreAdapter
import com.example.askhat.decathlon.store.StoreService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_basket.*
import org.koin.android.ext.android.inject

class BasketFragment : Fragment(), StoreAdapter.ProductItemClicked {

    private val service: StoreService by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProducts()
    }

    @SuppressLint("CheckResult")
    private fun getProducts(){
        service.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            val list = ArrayList<Product>()
                            for(i in it){
                                if(i.favorite == 1) list.add(i)
                            }
                            setProducts(list)
                        },
                        {
                            Logger.msg("accepted", it.message)
                        })
    }

    private fun setProducts(products: ArrayList<Product>) {
        recyclerBasket.layoutManager = GridLayoutManager(context, 1)
        recyclerBasket.adapter = StoreAdapter(activity!!, products, this)
    }

    @SuppressLint("CheckResult")
    override fun onProductLiked(product: Product) {
        service.updateProduct(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Logger.msg("accepted", it.string())
                            getProducts()
                        },
                        {
                            Logger.msg("accepted", it.message)
                        })
    }

    override fun onProductClicked(product: Product) {
        Logger.msg("accepted", product)
        val intent = Intent(activity, ProductDetailsActivity::class.java)
        intent.putExtra(EXTRA_DETAILS,product as Parcelable)
        startActivity(intent)
    }


}
