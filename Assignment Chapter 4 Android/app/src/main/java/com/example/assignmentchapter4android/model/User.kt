package com.example.assignmentchapter4android.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val email: String,
    @SerializedName("image")
    val imageUrl: String
)