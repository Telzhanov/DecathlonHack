package com.example.askhat.decathlon.entities

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@SuppressLint("ParcelCreator")
data class Product(
        @SerializedName("id") var id: String,
        @SerializedName("title") var title: String,
        @SerializedName("description") var description: String,
        @SerializedName("size") var size: String,
        @SerializedName("price") var price: Int,
        @SerializedName("photos") var photos: String,
        @SerializedName("favorite") var favorite: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(size)
        parcel.writeInt(price)
        parcel.writeString(photos)
        parcel.writeInt(favorite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}