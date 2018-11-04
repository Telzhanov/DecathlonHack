package com.example.askhat.decathlon.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("date") val date: String,
    @SerializedName("description") val description: String,
    @SerializedName("docoins") val docoins: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("photos") val photos: String,
    @SerializedName("title") val title: String,
    @SerializedName("typeOf") val typeOf: String
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(description)
        parcel.writeInt(docoins)
        parcel.writeInt(id)
        parcel.writeInt(price)
        parcel.writeString(photos)
        parcel.writeString(title)
        parcel.writeString(typeOf)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}

data class EventResponse(
    @SerializedName("code") val code:Int,
    @SerializedName("new_coins") val coins:Int
)