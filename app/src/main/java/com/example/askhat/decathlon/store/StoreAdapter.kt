package com.example.askhat.decathlon.store

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.entities.Product
import kotlinx.android.synthetic.main.item_store_product.view.*


class StoreAdapter(private val context : Context,
                   private val items : ArrayList<Product>,
                   private val listener : ProductItemClicked)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return TodoViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_store_product, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val product = items[p1]
        if (product.photos != "") {
            Glide.with(context)
                    .load(product.photos)
                    .into(p0.itemView.imageViewCardProduct)
        }
        p0.itemView.textViewPriceProduct.text = product.price.toString()

        p0.itemView.imageViewFavoriteProductLike.setOnClickListener {
            if(!items[p1].favorite){
                items[p1].favorite = true
                listener.onProductLiked(items[p1])
                p0.itemView.imageViewFavoriteProductLike.setBackgroundResource(R.drawable.ic_favorite)
            }else{
                items[p1].favorite = false
                listener.onProductLiked(items[p1])
                p0.itemView.imageViewFavoriteProductLike.setBackgroundResource(R.drawable.ic_favorite_border)
            }
        }

        p0.itemView.setOnClickListener {
            listener.onProductClicked(product)
        }
    }

    class TodoViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    interface ProductItemClicked {
        fun onProductClicked (product : Product)
        fun onProductLiked(product: Product)
    }
}