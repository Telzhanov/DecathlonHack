package com.example.askhat.decathlon.store

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.Product
import com.example.askhat.decathlon.menu.MainMenuActivity
import com.example.askhat.decathlon.menu.MainMenuActivity.Companion.user
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.card_events.view.*
import org.koin.android.ext.android.inject
import org.w3c.dom.Text

class ProductDetailsActivity : AppCompatActivity() {

    private var isFav = 0
    private val service: StoreService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val product = intent.getParcelableExtra<Product>(EXTRA_DETAILS)
        setSupportActionBar(findViewById(R.id.toolbar_product_details))
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        }

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
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Покупка товара")
            var view: View = LayoutInflater.from(this).inflate(R.layout.buy_product_dialog,null)
            var tovar:TextView = view.findViewById(R.id.nameProduct)
            var price:TextView = view.findViewById(R.id.priceProduct)
            var ostatok:TextView = view.findViewById(R.id.ostatok)
            var summa:EditText = view.findViewById(R.id.summaCoin)
            var tagiplus:TextView = view.findViewById(R.id.bonusTextView)
            tovar.text = product?.title
            price.text = product?.price.toString() + "Тн."
            ostatok.text = "( Остаток:" + MainMenuActivity.user?.decopoint + ")"
            tagiplus.text = "+" + product?.docoins.toString()
            builder.setView(view)
                    .setPositiveButton("Купить",object: DialogInterface.OnClickListener{
                        @SuppressLint("CheckResult")
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            service.buyProduct(MainMenuActivity.user?.id!!,product.id,summa.text.toString().toInt())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe{
                                        user = it.user
                                        finish()
                                    }
                        }
                    })
                    .setNegativeButton("Отмена", object: DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {

                        }

                    })
                    .create()
                    .show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
