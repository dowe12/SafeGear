package com.example.safegear.network

import com.example.safegear.model.LoginResponse
import com.example.safegear.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("login")
    suspend fun signIn(@Body user: UserResponse): Response<LoginResponse>

}