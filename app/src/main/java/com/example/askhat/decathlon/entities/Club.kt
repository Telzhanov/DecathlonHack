package com.example.askhat.decathlon.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Club(
        @SerializedName("id") var id : Int,
        @SerializedName("title") var title: String,
        @SerializedName("description") var description: String,
        @SerializedName("price") var price:Int,
        @SerializedName("photos") var photos: String,
        @SerializedName("subscriber") var subscriber: Boolean,
        @SerializedName("decocoins") var decocoins: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(price)
        parcel.writeString(photos)
        parcel.writeByte(if (subscriber) 1 else 0)
        parcel.writeInt(decocoins)
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