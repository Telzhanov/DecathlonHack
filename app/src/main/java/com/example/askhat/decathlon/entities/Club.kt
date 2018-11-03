package com.example.askhat.decathlon.entities

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class Club(
        @SerializedName("title") var title: String,
        @SerializedName("description") var description: String,
        @SerializedName("price") var price:Int,
        @SerializedName("photos") var photos: String,
        @SerializedName("subscriber") var subscriber: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(price)
        parcel.writeString(photos)
        parcel.writeByte(if (subscriber) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Club> {
        override fun createFromParcel(parcel: Parcel): Club {
            return Club(parcel)
        }

        override fun newArray(size: Int): Array<Club?> {
            return arrayOfNulls(size)
        }
    }
}