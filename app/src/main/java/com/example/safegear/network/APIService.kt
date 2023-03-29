package com.example.safegear.network

import com.example.safegear.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {

    @POST("login")
    suspend fun signIn(@Body user: UserResponse): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body user: UserBodyRegister): Response<LoginResponse>

    @GET("usuario/{userId}")
    suspend fun getUserById(@Path("userId") id: Int): Response<UsuarioModel>

    @POST("usuario/update/{userId}")
    suspend fun userUpdate(@Path("userId") id: Int, @Body user : UsuarioModel): Response<UsuarioResponse>

    @DELETE("usuario/{userId}")
    suspend fun userDelete(@Path("userId") id: Int): Response<UserResponse>

    @POST("vehiculo")
    suspend fun vehicleRegister(@Body vehicle: VehiculoModel): Response<VehiculoResponse>

    @GET("vehiculo/{vehicleId}")
    suspend fun getVehicleById(@Path("vehicleId") id: Int): Response<VehiculoModel>

    @GET("vehiculos/{userId}")
    suspend fun getVehiclesByUserId(@Path("userId") id: Int): Response<List<VehiculoModel>>
    @POST("vehiculo/update/{vehicleId}")
    suspend fun vehicleUpdate(@Path("vehicleId") id: Int, @Body vehicle: VehiculoModel): Response<VehiculoResponse>
    @DELETE("vehiculo/{vehicleId}")
    suspend fun vehicleDelete(@Path("vehicleId") id: Int): Response<VehiculoResponse>


}
