package com.example.assignmentchapter4android.repository

import com.example.assignmentchapter4android.model.CreateUserRequest
import com.example.assignmentchapter4android.model.LoginRequest
import com.example.assignmentchapter4android.model.LoginResponse
import com.example.assignmentchapter4android.model.User
import com.example.assignmentchapter4android.model.UsersListResponse
import com.example.assignmentchapter4android.network.NetworkManager
import com.example.assignmentchapter4android.network.NetworkResult

class UserRepository(private val networkManager: NetworkManager) {

    suspend fun login(
        request: LoginRequest
    ): NetworkResult<LoginResponse> {
        return networkManager.login(
            request
        )
    }

    suspend fun getUsers(): NetworkResult<UsersListResponse> {
        return networkManager.getUsers()
    }

    suspend fun getUser(userId: Int): NetworkResult<User> {
        return networkManager.getUser(userId)
    }

    suspend fun createUser(request: CreateUserRequest): NetworkResult<User> {
        return networkManager.createUser(request)
    }
}