package com.example.askhat.decathlon.store


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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_store.*
import org.koin.android.ext.android.inject

const val EXTRA_DETAILS = "EXTRA_DETAILS"

class StoreFragment : Fragment(), StoreAdapter.ProductItemClicked {

    private val service: StoreService by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            setProducts(it as ArrayList<Product>)
                        },
                        {
                            Logger.msg("accepted", it.message)
                        })
    }

    private fun setProducts(products: ArrayList<Product>) {
        recyclerStore.layoutManager = GridLayoutManager(context,2)
        recyclerStore.adapter = StoreAdapter(activity!!, products, this)
    }

    @SuppressLint("CheckResult")
    override fun onProductLiked(product: Product) {
        service.updateProduct(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Logger.msg("accepted", it.string())
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
