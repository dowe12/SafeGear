package com.example.safegear.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.safegear.EditarVehiculo
import com.example.safegear.HomeVehiculo
import com.example.safegear.R
import com.example.safegear.databinding.ItemVehiculoBinding
import com.example.safegear.model.VehiculoModel
import com.example.safegear.network.APIService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VehiculoViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = ItemVehiculoBinding.bind(view)
    private val context = itemView.context

    fun render(vehiculoModel : VehiculoModel){
        binding.tvPlacaListVehiculo.text = vehiculoModel.placa
        binding.imvVerVehiculo.setOnClickListener{
            val intent = Intent(itemView.context, EditarVehiculo::class.java)
            intent.putExtra("vehicleId", vehiculoModel.vehicle_id )
            itemView.context.startActivity(intent)
            (context as Activity).finish()
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
                vehiculoModel.vehicle_id?.let { showConfirmationMessage(it) }
                dialog.dismiss()
            }
            .show()
    }

    private fun showConfirmationMessage(vehicleId: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).vehicleDelete(vehicleId.toInt())
            val dataVehicle = call.body()
            binding.root.handler.post{
                when (dataVehicle?.status) {
                    "success" -> {
                        Toast.makeText(itemView.context, "El vehículo ha sido eliminado", Toast.LENGTH_SHORT).show()
                    }
                    "invalid" -> {
                        Toast.makeText(itemView.context, "Error al eliminar", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Log.e("API", "" + call)
                        Toast.makeText(itemView.context, "Error de servidor", Toast.LENGTH_SHORT).show()
                    }
                }
                val intent = Intent(itemView.context, HomeVehiculo::class.java)
                itemView.context.startActivity(intent)
                (context as Activity).finish()
            }
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(binding.root.context.getString(R.string.url_base))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
