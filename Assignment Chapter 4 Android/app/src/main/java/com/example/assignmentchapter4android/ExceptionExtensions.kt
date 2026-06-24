package com.example.assignmentchapter4android

fun Exception.toUserMessage(): String {
    return when (this) {

        is java.net.UnknownHostException ->
            "No internet connection"

        is java.net.SocketTimeoutException ->
            "Request timed out. Please try again"

        is java.net.ConnectException ->
            "Unable to connect to server"

        else ->
            "Something went wrong. Please try again"
    }
}