package com.example.safegear.model

import com.google.gson.annotations.SerializedName

data class MantenimientoModel (
    @SerializedName("maintenance_id") val mantenimiento_id: String?,
    @SerializedName("vehicle_id") val vehicle_id: String?,
    @SerializedName("titulo") val titulo: String?,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("url_foto") val url_foto: String,
    @SerializedName("fecha_mantenimiento") val fecha_mantenimiento: String?,
    @SerializedName("nombre_mecanico") val nombre_mecanico: String?,
    @SerializedName("precio") val precio: String
)