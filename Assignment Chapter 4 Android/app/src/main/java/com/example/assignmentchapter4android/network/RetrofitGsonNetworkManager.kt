package com.example.assignmentchapter4android.network

import com.example.assignmentchapter4android.model.CreateUserRequest
import com.example.assignmentchapter4android.model.LoginRequest
import com.example.assignmentchapter4android.model.LoginResponse
import com.example.assignmentchapter4android.model.User
import com.example.assignmentchapter4android.model.UsersListResponse
import com.example.assignmentchapter4android.toUserMessage

class RetrofitGsonNetworkManager : NetworkManager {

    override suspend fun login(request: LoginRequest): NetworkResult<LoginResponse> {
        return try {
            val response = RetrofitClient.apiService.login(request)
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error("Login Failed")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.toUserMessage())
        }
    }

    override suspend fun getUsers(): NetworkResult<UsersListResponse> {
        return try {
            val response = RetrofitClient.apiService.getUsers()
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error("Failed to load data")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.toUserMessage())
        }
    }

    override suspend fun createUser(request: CreateUserRequest): NetworkResult<User> {
        return try {
            val response = RetrofitClient.apiService.createUser(request)
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error("Failed to create user")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.toUserMessage())
        }
    }

    override suspend fun getUser(userId: Int): NetworkResult<User> {
        return try {
            val response = RetrofitClient.apiService.getUser(userId)
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error("Failed to load data")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.toUserMessage())
        }
    }
}