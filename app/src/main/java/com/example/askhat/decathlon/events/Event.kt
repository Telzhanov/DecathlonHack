package com.example.askhat.decathlon.events

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("date") val date: String,
    @SerializedName("description") val description: String,
    @SerializedName("docoins") val docoins: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("photos") val photos: String,
    @SerializedName("title") val title: String,
    @SerializedName("typeOf") val typeOf: String
)

data class EventResponse(
    @SerializedName("code") val code:Int,
    @SerializedName("new_coins") val coins:Int
)