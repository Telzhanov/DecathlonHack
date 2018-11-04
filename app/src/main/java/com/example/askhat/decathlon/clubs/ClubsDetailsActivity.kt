package com.example.askhat.decathlon.clubs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.auth.User
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.Club
import com.example.askhat.decathlon.menu.MainMenuActivity
import com.example.askhat.decathlon.store.ClubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product_details.*
import org.koin.android.ext.android.inject

class ClubsDetailsActivity : AppCompatActivity() {

    private var isFav = false
    private val service: ClubService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clubs_details)

        setSupportActionBar(findViewById(R.id.toolbar_club_details))
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        }


        val club = intent.getParcelableExtra<Club>(EXTRA_CLUB_DETAILS)

        if(club.subscriber){
            isFav = club.subscriber
            detailsLikeFab.setImageResource(R.drawable.ic_favorite)
        }else{
            isFav = club.subscriber
            detailsLikeFab.setImageResource(R.drawable.ic_favorite_border)
        }
//        main_backdrop.setImageURI(product.photos)
        Glide.with(this)
                .load(club.photos)
                .into(main_backdrop)

        tvTitle.text = club.title
        tvContent.text = club.description
        tvPrice.text = club.price.toString()

        detailsLikeFab.setOnClickListener{ view ->
            if(isFav){
                isFav = false
                club.subscriber = false
                detailsLikeFab.setImageResource(R.drawable.ic_favorite_border)
            }else{
                isFav = true
                club.subscriber = true
                detailsLikeFab.setImageResource(R.drawable.ic_favorite)
            }
        }
        Logger.msg("accepted", club)


        buyProductBtn.setOnClickListener{ view ->
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Покупка товара")
            var view: View = LayoutInflater.from(this).inflate(R.layout.subscribe_club_dialog,null)
            var tovar: TextView = view.findViewById(R.id.nameClub)
            var price: TextView = view.findViewById(R.id.priceClub)
            var tagiplus: TextView = view.findViewById(R.id.bonusTextView)
            tovar.text = club?.title
            price.text = club?.price.toString() + "Тн."
            tagiplus.text = "+" + club?.decocoins.toString()
            builder.setView(view)
                    .setPositiveButton("Купить",object: DialogInterface.OnClickListener{
                        @SuppressLint("CheckResult")
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            service.updateClub(club.id,MainMenuActivity.user?.id!!)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe{
                                        var user = User(MainMenuActivity.user?.age!!,MainMenuActivity.user?.city!!,MainMenuActivity.user?.clubs!!,
                                                it.new_coins,MainMenuActivity.user?.email!!,MainMenuActivity.user?.id!!,MainMenuActivity.user?.name!!,
                                                MainMenuActivity.user?.password!!,MainMenuActivity.user?.phone!!,MainMenuActivity.user?.photos!!,MainMenuActivity.user?.products!!,
                                                MainMenuActivity.user?.size!!,MainMenuActivity.user?.subscriptions!!)
                                        MainMenuActivity.user = user
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
