package com.example.assignmentchapter4android.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val userName: String,
    val password: String
)
