package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class MantenimientoModel (
    @SerializedName("status") val status: String?,
    @SerializedName("maintenance_id") val maintenance_id: String?,
    @SerializedName("vehiculo_id") val vehiculo_id: String?,
    @SerializedName("titulo") val titulo: String?,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("url_foto") val url_foto: String,
    @SerializedName("fecha") val fecha_mantenimiento: String?,
    @SerializedName("nombre_mecanico") val nombre_mecanico: String?,
    @SerializedName("precio") val precio: String
)