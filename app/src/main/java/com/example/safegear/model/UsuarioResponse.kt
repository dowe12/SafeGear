package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class UsuarioResponse(

    @SerializedName("id") val id: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("message") val message: String?

)