package com.example.askhat.decathlon.events

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.io.Serializable

interface EventService{
    @GET("get_events")
    fun getEvents():Observable<List<Event>>

    @POST("subscribe_event/")
    fun subscribe(@Query("user_id") user_id:Int,
                  @Query("event_id") event_id:Int):Observable<EventResponse>

    }
