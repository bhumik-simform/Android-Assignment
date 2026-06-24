package com.example.assignmentchapter4android.network

import com.example.assignmentchapter4android.model.CreateUserRequest
import com.example.assignmentchapter4android.model.LoginRequest
import com.example.assignmentchapter4android.model.LoginResponse
import com.example.assignmentchapter4android.model.User
import com.example.assignmentchapter4android.model.UsersListResponse
import com.example.assignmentchapter4android.toUserMessage
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class RetrofitManualNetworkManager : NetworkManager {
    override suspend fun login(request: LoginRequest): NetworkResult<LoginResponse> {
        return try {
            val requestBody = JSONObject()
                .put("username", request.userName)
                .put("password", request.password).toString()
                .toRequestBody("application/json".toMediaType())

            val response = RetrofitClient.apiManService.login(requestBody)
            if (response.isSuccessful && response.body() != null) {

                val jsonObject = JSONObject(
                    response.body()!!.string()
                )

                val id = jsonObject.getString("id").toInt()
                val accessToken = jsonObject.getString("accessToken").toString()
                val refreshToken = jsonObject.getString("refreshToken").toString()

                val loginResponse = LoginResponse(id, accessToken, refreshToken)

                NetworkResult.Success(loginResponse)

            } else {
                NetworkResult.Error("Login Failed")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.toUserMessage())
        }
    }

    override suspend fun getUsers(): NetworkResult<UsersListResponse> {
        return try {
            val response = RetrofitClient.apiManService.getUsers()

            if (response.isSuccessful && response.body() != null) {
                val jsonObject = JSONObject(
                    response.body()!!.string()
                )

                val usersArray = jsonObject.getJSONArray("users")

                val usersList = mutableListOf<User>()

                for (i in 0 until usersArray.length()) {

                    val userObject = usersArray.getJSONObject(i)

                    val id = userObject.getString("id").toInt()
                    val firstName = userObject.getString("firstName").toString()
                    val lastName = userObject.getString("lastName").toString()
                    val userName = userObject.getString("username").toString()
                    val email = userObject.getString("email").toString()
                    val imageUrl = userObject.getString("image").toString()

                    val user = User(
                        id = id,
                        firstName = firstName,
                        lastName = lastName,
                        userName = userName,
                        email = email,
                        imageUrl = imageUrl
                    )

                    usersList.add(user)
                }


                NetworkResult.Success(UsersListResponse(usersList))

            } else {
                NetworkResult.Error("Failed to load data")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.toUserMessage())
        }
    }

    override suspend fun createUser(request: CreateUserRequest): NetworkResult<User> {
        return try {
            val requestBody = JSONObject()
                .put("firstName", request.firstName)
                .put("lastName", request.lastName)
                .put("username", request.userName)
                .put("email", request.email)
                .put("image", request.imageUrl)
                .toString()
                .toRequestBody("application/json".toMediaType())

            val response = RetrofitClient.apiManService.createUser(requestBody)

            if (response.isSuccessful && response.body() != null) {
                val jsonObject = JSONObject(
                    response.body()!!.string()
                )

                val id = jsonObject.getString("id").toInt()
                val firstName = jsonObject.getString("firstName").toString()
                val lastName = jsonObject.getString("lastName").toString()
                val userName = jsonObject.getString("username").toString()
                val email = jsonObject.getString("email").toString()
                val imageUrl = jsonObject.getString("image").toString()

                val user = User(
                    id = id,
                    firstName = firstName,
                    lastName = lastName,
                    userName = userName,
                    email = email,
                    imageUrl = imageUrl
                )

                NetworkResult.Success(user)

            } else {
                NetworkResult.Error("Failed to create user")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.toUserMessage())
        }
    }

    override suspend fun getUser(userId: Int): NetworkResult<User> {
        return try {
            val response = RetrofitClient.apiManService.getUser(userId)
            if (response.isSuccessful && response.body() != null) {
                val jsonObject = JSONObject(
                    response.body()!!.string()
                )

                val id = jsonObject.getString("id").toInt()
                val firstName = jsonObject.getString("firstName").toString()
                val lastName = jsonObject.getString("lastName").toString()
                val userName = jsonObject.getString("username").toString()
                val email = jsonObject.getString("email").toString()
                val imageUrl = jsonObject.getString("image").toString()

                val user = User(
                    id = id,
                    firstName = firstName,
                    lastName = lastName,
                    userName = userName,
                    email = email,
                    imageUrl = imageUrl
                )

                NetworkResult.Success(user)

            } else {
                NetworkResult.Error("Failed to load data")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.toUserMessage())
        }
    }
}