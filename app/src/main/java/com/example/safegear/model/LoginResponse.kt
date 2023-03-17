package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id_user") var id_user: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("message") var message: String?,
    @SerializedName("jwt") var token: String?,
    @SerializedName("status") val status: String?
    )
