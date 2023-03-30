package com.example.safegear.model

import com.google.gson.annotations.SerializedName

class MaintenanceResponse (
    @SerializedName("id") val id: String?,
    @SerializedName("status") val status: String?
)