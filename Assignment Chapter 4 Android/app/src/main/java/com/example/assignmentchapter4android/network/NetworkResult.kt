package com.example.assignmentchapter4android.network

sealed class NetworkResult<out T> {

    data class Success<T>(
        val data: T
    ): NetworkResult<T>()

    data class Error(
        val networkError: String
    ): NetworkResult<Nothing>()
}