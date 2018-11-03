package com.example.askhat.decathlon.store

import com.example.askhat.decathlon.entities.Product
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StoreService {

    @GET("add_product/")
    fun getProducts() : Observable<List<Product>>

    @POST("update_product/")
    fun updateProduct(@Body post: Product) : Observable<ResponseBody>

}