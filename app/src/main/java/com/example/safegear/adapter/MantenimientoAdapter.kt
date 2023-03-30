package com.example.safegear.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.safegear.R
import com.example.safegear.model.MantenimientoModel
import com.example.safegear.model.VehiculoModel

class MantenimientoAdapter (private val mantenimientoList:List<MantenimientoModel>): RecyclerView.Adapter<MantenimientoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MantenimientoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MantenimientoViewHolder(layoutInflater.inflate(R.layout.item_mantenimiento, parent, false))
    }

    override fun onBindViewHolder(holder: MantenimientoViewHolder, position: Int) {
        val item = mantenimientoList[position]
        holder.render(item)
    }
    override fun getItemCount(): Int = mantenimientoList.size
    }