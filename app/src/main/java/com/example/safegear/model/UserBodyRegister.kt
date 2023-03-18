package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class UserBodyRegister(
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("correo") val correo: String?,
    @SerializedName("contrasenia") val contrasenia: String?,
)
