package com.example.safegear.adapter

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.safegear.EditarVehiculo
import com.example.safegear.databinding.ItemVehiculoBinding
import com.example.safegear.model.VehiculoModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class VehiculoViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = ItemVehiculoBinding.bind(view)
    fun render(vehiculoModel : VehiculoModel){
        binding.tvPlacaListVehiculo.text = vehiculoModel.placa

        binding.imvVerVehiculo.setOnClickListener{
            val intent = Intent(itemView.context, EditarVehiculo::class.java)
            intent.putExtra("vehicleId", vehiculoModel.vehicle_id )
            itemView.context.startActivity(intent)
        }

        binding.imvEliminarVehiculo.setOnClickListener{
            showAlertDialog(vehiculoModel)
        }
    }

    private fun showAlertDialog(vehiculoModel: VehiculoModel){
        MaterialAlertDialogBuilder(itemView.context)
            .setTitle("Eliminar vehículo")
            .setMessage("¿Deseas eliminar el vehículo?")
            .setNegativeButton("No"){dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Si"){dialog, _ ->
                //Eliminar vehiculo
                showConfirmationMessage()
                dialog.dismiss()
            }
            .show()
    }

    private fun showConfirmationMessage(){
        Toast.makeText(itemView.context, "El vehículo ha sido eliminado", Toast.LENGTH_SHORT).show()
    }
}
