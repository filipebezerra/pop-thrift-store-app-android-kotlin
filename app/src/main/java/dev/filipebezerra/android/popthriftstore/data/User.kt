package dev.filipebezerra.android.popthriftstore.data

import java.util.*

data class User(
    val id: String = UUID.randomUUID().toString(),
    val email: String,
    val fullName: String
)