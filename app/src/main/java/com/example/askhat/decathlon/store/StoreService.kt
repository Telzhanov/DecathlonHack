package com.example.askhat.decathlon.store

import com.example.askhat.decathlon.entities.Product
import io.reactivex.Observable
import retrofit2.http.GET

interface StoreService {

    @GET("add_product/")
    fun getProducts() : Observable<List<Product>>

}