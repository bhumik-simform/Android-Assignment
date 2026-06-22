package com.example.assignmentchapter4android.network

import com.example.assignmentchapter4android.model.CreateUserRequest
import com.example.assignmentchapter4android.model.LoginRequest
import com.example.assignmentchapter4android.model.LoginResponse
import com.example.assignmentchapter4android.model.User

interface NetworkManager {

    suspend fun login(request: LoginRequest): NetworkResult<LoginResponse>

    suspend fun getUsers(): NetworkResult<List<User>>

    suspend fun createUser(request: CreateUserRequest): NetworkResult<User>

    suspend fun getUser(id: Int): NetworkResult<User>

}