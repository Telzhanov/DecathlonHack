package com.example.askhat.decathlon.entities

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class History(
        @SerializedName("clubs") var clubs: List<Club>,
        @SerializedName("products") var products: List<Product>,
        @SerializedName("events") var events: List<Event>
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(Club),
            parcel.createTypedArrayList(Product),
            parcel.createTypedArrayList(Event)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(clubs)
        parcel.writeTypedList(products)
        parcel.writeTypedList(events)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<History> {
        override fun createFromParcel(parcel: Parcel): History {
            return History(parcel)
        }

        override fun newArray(size: Int): Array<History?> {
            return arrayOfNulls(size)
        }
    }
}