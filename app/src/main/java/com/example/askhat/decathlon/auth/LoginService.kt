package com.example.askhat.decathlon.auth

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService{
    @GET("authorize")
    fun authorize(@Query("email") email:String,
                  @Query("password") password:String):Observable<LoginResponse>
}