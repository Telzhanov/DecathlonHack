package com.example.askhat.decathlon.auth

import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService{
    @POST("authorize")
    fun authorize(@Body body: String)
}