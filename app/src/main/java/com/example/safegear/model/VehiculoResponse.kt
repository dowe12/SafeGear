package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class VehiculoResponse(

    @SerializedName("user_id") val user_id: String?,
    @SerializedName("id_clase_vehiculo") val id_clase_vehiculo: String?,
    @SerializedName("id_combustible") val id_combustible: String?,
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