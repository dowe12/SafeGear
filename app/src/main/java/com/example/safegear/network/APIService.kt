package com.example.safegear.network

import com.example.safegear.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {

    @POST("login")
    suspend fun signIn(@Body user: UserResponse): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body user: UserBodyRegister): Response<LoginResponse>

    @POST("vehiculo")
    suspend fun vehicleRegister(@Body vehicle: VehiculoModel): Response<VehiculoResponse>

    @GET("vehiculo/{idVehicle}")
    suspend fun getVehicleById(@Path("idVehicle") id: Int): Response<VehiculoModel>

    @POST("vehiculo/update")
    suspend fun vehicleEdit(@Body vehicle: VehicleBodyEdit): Response<VehiculoResponse>
}