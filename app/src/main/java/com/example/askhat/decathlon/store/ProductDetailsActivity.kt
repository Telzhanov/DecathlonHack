package com.example.askhat.decathlon.store

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.Product
import com.example.askhat.decathlon.menu.MainMenuActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product_details.*
import org.koin.android.ext.android.inject

class ProductDetailsActivity : AppCompatActivity() {

    private var isFav = 0
    private val service: StoreService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val product = intent.getParcelableExtra<Product>(EXTRA_DETAILS)


        if(product.favorite == 1){
            isFav = product.favorite
            detailsLikeFab.setImageResource(R.drawable.ic_favorite)
        }else{
            isFav = product.favorite
            detailsLikeFab.setImageResource(R.drawable.ic_favorite_border)
        }
//        main_backdrop.setImageURI(product.photos)
        Glide.with(this)
                .load(product.photos)
                .into(main_backdrop)

        tvTitle.text = product.title
        tvContent.text = product.description
        tvPrice.text = product.price.toString()
        tvSize.text = product.size

        detailsLikeFab.setOnClickListener{ view ->
            if(isFav == 1){
                isFav = 0
                product.favorite = 0
                detailsLikeFab.setImageResource(R.drawable.ic_favorite_border)
            }else{
                isFav = 1
                product.favorite = 1
                detailsLikeFab.setImageResource(R.drawable.ic_favorite)
            }

            service.updateProduct(MainMenuActivity.user!!.id, product.id)
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
        Logger.msg("accepted", product)


        buyProductBtn.setOnClickListener{

        }
    }

}
