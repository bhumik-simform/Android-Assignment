package com.example.assignmentchapter4android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val email: String,
    val imageUrl: String
): Parcelable
