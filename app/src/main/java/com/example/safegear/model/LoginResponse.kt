package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id_user") var id_user: String?,
    @SerializedName("rol_id") var rol_id: String?,
    @SerializedName("identificacion") var identificacion: String?,
    @SerializedName("nombre") var nombre: String?,
    @SerializedName("apellido") var apellido: String?,
    @SerializedName("telefono") var telefono: String?,
    @SerializedName("jwt") var jwt: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("message") val message: String?
    )
