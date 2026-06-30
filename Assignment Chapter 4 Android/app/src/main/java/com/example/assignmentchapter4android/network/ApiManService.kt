package com.example.assignmentchapter4android.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiManService {

    @POST("auth/login")
    suspend fun login(@Body request: RequestBody): Response<ResponseBody>

    @GET("users")
    suspend fun getUsers(): Response<ResponseBody>

    @GET("users/{id}")
    suspend fun getUser(@Path("id")userId: Int): Response<ResponseBody>

    @POST("users/add")
    suspend fun createUser(@Body request: RequestBody): Response<ResponseBody>
}