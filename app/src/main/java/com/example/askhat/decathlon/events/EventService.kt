package com.example.askhat.decathlon.events

import com.example.askhat.decathlon.entities.Event
import com.example.askhat.decathlon.entities.EventResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EventService{
    @GET("get_events")
    fun getEvents():Observable<List<Event>>

    @POST("subscribe_event/")
    fun subscribe(@Query("user_id") user_id:Int,
                  @Query("event_id") event_id:Int):Observable<EventResponse>

    }
