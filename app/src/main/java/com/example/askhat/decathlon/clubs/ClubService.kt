package com.example.askhat.decathlon.store

import com.example.askhat.decathlon.entities.Club
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ClubService {

    @GET("get_clubs/")
    fun getClubs(@Query("user_id") id: Int) : Observable<List<Club>>

    @POST("update_product/")
    fun updateClub(@Body post: Club,
                   @Query("user_id") id: Int) : Observable<ResponseBody>

}