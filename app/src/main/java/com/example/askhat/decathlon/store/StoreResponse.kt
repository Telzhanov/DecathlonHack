package com.example.askhat.decathlon.store

import com.example.askhat.decathlon.auth.User

data class ProductResponse(
    val code: Int,
    val user: User
)

