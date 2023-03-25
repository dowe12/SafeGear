package com.example.safegear.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.safegear.R
import com.example.safegear.model.VehiculoModel

class VehiculoAdapter: RecyclerView.Adapter<VehiculoViewHolder>() {

    private val vehiculos = listOf(
        VehiculoModel("AAA-111", "Campero", "ACPM", "AAA-AAA","W","W","W",
        "W","","","",""),
        VehiculoModel("BBB-111", "Campero", "ACPM", "BBB-AAA","A","A","A",
            "A","","","",""),
        VehiculoModel("CCC-111", "Campero", "ACPM", "CCC-AAA","C","C","C",
            "C","","","","")
    )

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
         val layoutInflater = LayoutInflater.from(parent.context)
         return VehiculoViewHolder(layoutInflater.inflate(R.layout.item_vehiculo, parent, false))
     }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        val item = vehiculos[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = vehiculos.size
}
