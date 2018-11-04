package com.example.askhat.decathlon.auth

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    val code: Int,
    val user: User
)

data class User(
    @SerializedName("age") val age: Int,
    @SerializedName("city") val city: String,
    @SerializedName("clubs") val clubs: Any,
    @SerializedName("decopoint") val decopoint: Int,
    @SerializedName("email") val email: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("photos") val photos: String,
    @SerializedName("products") val products: Any,
    @SerializedName("size") val size: String,
    @SerializedName("subscriptions") val subscriptions: List<Int>
):Serializable