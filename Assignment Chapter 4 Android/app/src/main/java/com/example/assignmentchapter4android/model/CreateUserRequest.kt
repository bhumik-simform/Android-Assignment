package com.example.assignmentchapter4android.model

data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val email: String,
    val avatarURL: String
)
