package com.example.askhat.decathlon.clubs

data class ClubResponse(
    val clubs: List<Club>,
    val code: Int,
    val new_coins: Int
)

data class Club(
    val decocoins: Int,
    val description: String,
    val id: Int,
    val photos: String,
    val price: Int,
    val subscriber: Boolean,
    val title: String
)