package com.example.safegear.network

import com.example.safegear.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("login")
    suspend fun signIn(@Body user: UserResponse): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body user: UserBodyRegister): Response<LoginResponse>

    @POST("vehiculo")
    suspend fun vehicleRegister(@Body vehicle: VehiculoBodyRegister): Response<VehiculoResponse>

}