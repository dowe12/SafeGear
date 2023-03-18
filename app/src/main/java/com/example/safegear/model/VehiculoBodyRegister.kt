package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class VehiculoBodyRegister(

    @SerializedName("user_id") val user_id: String?,
    @SerializedName("clase_vehiculo") val clase_vehiculo: String?,
    @SerializedName("combustible") val combustible: String?,
    @SerializedName("placa") val placa: String?,
    @SerializedName("marca") val marca: String?,
    @SerializedName("modelo") val modelo: String?,
    @SerializedName("color") val color: String?,
    @SerializedName("cilindraje") val cilindraje: String?,
    @SerializedName("fecha_inicio_SOAT") val fecha_inicio_SOAT: String,
    @SerializedName("fecha_fin_SOAT") val fecha_fin_SOAT: String,
    @SerializedName("fecha_inicio_tecno") val fecha_inicio_tecno: String,
    @SerializedName("fecha_fin_tecno") val fecha_fin_tecno: String
)