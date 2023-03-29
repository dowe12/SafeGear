package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class UsuarioModel(
    @SerializedName("status") val status: String?,
    @SerializedName("user_id") val user_id: String?,
    @SerializedName("rol_id") val rol_id: String?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("apellido") val apellido: String?,
    @SerializedName("telefono") val telefono: String?,
    @SerializedName("identificacion") val identificacion: String?,
    @SerializedName("correo") val correo: String?,
    @SerializedName("contrasenia") val contrasenia: String?
)