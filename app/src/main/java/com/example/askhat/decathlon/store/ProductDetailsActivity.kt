package com.example.askhat.decathlon.store

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.entities.Product

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val product = intent.getParcelableExtra<Product>(EXTRA_DETAILS)

        Logger.msg("accepted", product)
    }
}
