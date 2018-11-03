package com.example.askhat.decathlon.clubs

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.Club
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
            //TODO dialog

            subscribe(club)
        }
    }


    @SuppressLint("CheckResult")
    private fun subscribe(club: Club){
        club.subscriber = true
        service.updateClub(club)
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
}
