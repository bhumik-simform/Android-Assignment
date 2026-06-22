package com.example.assignmentchapter4android.network

import com.example.assignmentchapter4android.NetWorkError

sealed class NetworkResult<out T> {

    data class Success<T>(
        val data: T
    ): NetworkResult<T>()

    data class Error(
        val networkError: NetWorkError
    ): NetworkResult<Nothing>()
}