package com.example.askhat.decathlon.store

import com.example.askhat.decathlon.entities.Product
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StoreService {

    @GET("get_products/")
    fun getProducts(@Query("user_id")id:Int) : Observable<List<Product>>

    @POST("update_product/")
    fun updateProduct(@Query("user_id") user_id: Int,
                      @Query("product_id") product_id:Int) : Observable<ResponseBody>
    @POST("buy_product/")
    fun buyProduct(@Query("user_id") user_id: Int,
                   @Query("product_id") product_id: Int,
                   @Query("used_coins") used_coin:Int):Observable<ProductResponse>
}