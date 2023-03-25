package com.example.safegear.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.safegear.R
import com.example.safegear.model.VehiculoModel

class VehiculoAdapter(private val vehiculoList:List<VehiculoModel>): RecyclerView.Adapter<VehiculoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VehiculoViewHolder(layoutInflater.inflate(R.layout.item_vehiculo, parent, false))
    }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        val item = vehiculoList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = vehiculoList.size

}
