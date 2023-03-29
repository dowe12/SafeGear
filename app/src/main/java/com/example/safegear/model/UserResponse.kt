package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("correo") val correo: String?,
    @SerializedName("contrasenia") val contrasenia: String?,
)
