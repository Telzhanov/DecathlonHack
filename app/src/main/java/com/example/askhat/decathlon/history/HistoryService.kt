package com.example.askhat.decathlon.history

import com.example.askhat.decathlon.entities.History
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryService{

    @GET("get_history/")
    fun getHistory(@Query("user_id") id: Int) : Observable<History>

}