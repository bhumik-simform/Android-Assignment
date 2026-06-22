package com.example.assignmentchapter4android.network

import com.example.assignmentchapter4android.model.CreateUserRequest
import com.example.assignmentchapter4android.model.LoginRequest
import com.example.assignmentchapter4android.model.LoginResponse
import com.example.assignmentchapter4android.model.User
import com.example.assignmentchapter4android.model.UsersListResponse

class RetrofitManualNetworkManager: NetworkManager {
    override suspend fun login(request: LoginRequest): NetworkResult<LoginResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(): NetworkResult<UsersListResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(request: CreateUserRequest): NetworkResult<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: Int): NetworkResult<User> {
        TODO("Not yet implemented")
    }
}