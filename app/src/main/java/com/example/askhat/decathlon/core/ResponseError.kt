package com.example.askhat.decathlon.core

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)