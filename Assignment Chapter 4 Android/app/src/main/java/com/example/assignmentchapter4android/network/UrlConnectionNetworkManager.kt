package com.example.assignmentchapter4android.network

import com.example.assignmentchapter4android.model.CreateUserRequest
import com.example.assignmentchapter4android.model.LoginRequest
import com.example.assignmentchapter4android.model.LoginResponse
import com.example.assignmentchapter4android.model.User
import com.example.assignmentchapter4android.model.UsersListResponse
import com.example.assignmentchapter4android.toUserMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class UrlConnectionNetworkManager : NetworkManager {

    val baseUrl = "https://dummyjson.com/"

    override suspend fun login(request: LoginRequest): NetworkResult<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val requestBody = JSONObject()
                    .put("username", request.userName)
                    .put("password", request.password)
                    .toString()

                val response = makeRequest(
                    endpoint = "auth/login",
                    method = "POST",
                    requestBody
                )
                if (response.code in 200..299) {
                    val jsonObject = JSONObject(response.body)

                    val id = jsonObject.getString("id").toInt()
                    val accessToken = jsonObject.getString("accessToken").toString()
                    val refreshToken = jsonObject.getString("refreshToken").toString()

                    val loginResponse = LoginResponse(id, accessToken, refreshToken)

                    NetworkResult.Success(loginResponse)

                } else {
                    NetworkResult.Error("${response.code}: ${response.body}")
                }

            } catch (e: Exception) {
                NetworkResult.Error(e.toUserMessage())
            }
        }
    }

    override suspend fun getUsers(): NetworkResult<UsersListResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = makeRequest(
                    endpoint = "users",
                    method = "GET",
                    null
                )

                if (response.code in 200..299) {
                    val jsonObject = JSONObject(
                        response.body
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
                    NetworkResult.Error("${response.code}: ${response.body}")
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.toUserMessage())
            }
        }
    }

    override suspend fun createUser(request: CreateUserRequest): NetworkResult<User> {
        return withContext(Dispatchers.IO) {
            try {

                val requestBody = JSONObject()
                    .put("firstName", request.firstName)
                    .put("lastName", request.lastName)
                    .put("username", request.userName)
                    .put("email", request.email)
                    .put("image", request.imageUrl)
                    .toString()


                val response = makeRequest(
                    endpoint = "users/add",
                    method = "POST",
                    requestBody
                )

                if (response.code in 200..299) {
                    val jsonObject = JSONObject(
                        response.body
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
                    NetworkResult.Error("${response.code}: ${response.body}")
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.toUserMessage())
            }
        }
    }

    override suspend fun getUser(userId: Int): NetworkResult<User> {
        return withContext(Dispatchers.IO) {
            try {
                val response = makeRequest(
                    endpoint = "user/$userId",
                    method = "GET",
                    null
                )

                if (response.code in 200..299) {
                    val jsonObject = JSONObject(
                        response.body
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
                    NetworkResult.Error("${response.code}: ${response.body}")
                }

            } catch (e: Exception) {
                NetworkResult.Error(e.toUserMessage())
            }
        }
    }

    private data class ConnectionResult(
        val code: Int,
        val body: String
    )


    private fun makeRequest(
        endpoint: String,
        method: String,
        requestBody: String?
    ): ConnectionResult {

        val url = URL(baseUrl + endpoint)

        with(url.openConnection() as HttpURLConnection){
            try {

                requestMethod = method

                setRequestProperty(
                    "Content-Type",
                    "application/json"
                )

                if (requestBody != null) {
                    doOutput = true
                    outputStream.use {
                        it.write(
                            requestBody.toByteArray()
                        )
                    }
                }

                val responseCode = this.responseCode

                val stream =
                    if (responseCode in 200..299)
                        inputStream
                    else errorStream

                val responseBody = stream.bufferedReader().use {
                    it.readText()
                }

                return ConnectionResult(
                    responseCode,
                    responseBody
                )

            } finally {
                disconnect()
            }
        }
    }
}