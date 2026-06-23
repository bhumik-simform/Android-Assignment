package com.example.assignmentchapter4android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    @SerializedName("username")
    val userName: String,
    val email: String,
    val imageUrl: String
): Parcelable
