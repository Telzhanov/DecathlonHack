package com.example.askhat.decathlon.store

import com.example.askhat.decathlon.clubs.ClubResponse
import com.example.askhat.decathlon.entities.Club
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ClubService {

    @GET("get_clubs/")
    fun getClubs(@Query("user_id") id: Int) : Observable<List<Club>>

    @POST("subscribe_club/")
    fun updateClub(@Query ("club_id") club_id: Int,
                   @Query("user_id") id: Int) : Observable<ClubResponse>

}