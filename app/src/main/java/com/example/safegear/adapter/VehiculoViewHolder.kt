package com.example.safegear.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.safegear.EditarVehiculo
import com.example.safegear.databinding.ItemVehiculoBinding
import com.example.safegear.model.VehiculoModel
import java.util.*

class VehiculoViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = ItemVehiculoBinding.bind(view)

    fun render(vehiculoModel : VehiculoModel){
        binding.tvPlacaListVehiculo.text = vehiculoModel.placa

        binding.imvVerVehiculo.setOnClickListener{
            val intent = Intent(itemView.context, EditarVehiculo::class.java)
            itemView.context.startActivity(intent)
        }
    }
}
