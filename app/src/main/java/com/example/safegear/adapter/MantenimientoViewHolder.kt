package com.example.safegear.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.safegear.EditarMantenimiento
import com.example.safegear.HomeMantenimiento
import com.example.safegear.R
import com.example.safegear.databinding.ItemMantenimientoBinding
import com.example.safegear.model.MantenimientoModel
import com.example.safegear.network.APIService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MantenimientoViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemMantenimientoBinding.bind(view)
    private val context = itemView.context

    fun render(mantenimientoModel: MantenimientoModel){
        binding.tvTituloMantenimientoListMantenimiento.text = mantenimientoModel.titulo
        binding.imvVerMantenimiento.setOnClickListener{
            val intent = Intent(itemView.context, EditarMantenimiento::class.java)
            intent.putExtra("maintenanceId", mantenimientoModel.maintenance_id)
            itemView.context.startActivity(intent)
            (context as Activity).finish()
        }

        binding.imvEliminarMantenimiento.setOnClickListener{
            showAlertDialog(mantenimientoModel)
        }
    }

    private fun showAlertDialog(mantenimientoModel: MantenimientoModel){
        MaterialAlertDialogBuilder(itemView.context)
            .setTitle("Eliminar mantenimiento")
            .setMessage("¿Deseas eliminar el mantenimiento?")
            .setNegativeButton("No"){dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Si"){dialog, _ ->
                mantenimientoModel.maintenance_id?.let { showConfirmationMessage(it) }
                dialog.dismiss()
            }
            .show()
    }

    private fun showConfirmationMessage(mantenimientoId: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).maintenanceDelete(mantenimientoId.toInt())
            val dataMaintenance = call.body()
            binding.root.handler.post{
                when (dataMaintenance?.status) {
                    "success" -> {
                        Toast.makeText(itemView.context, "El mantenimiento ha sido eliminado", Toast.LENGTH_SHORT).show()
                    }
                    "invalid" -> {
                        Toast.makeText(itemView.context, "Error al eliminar", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Log.e("API", "" + call)
                        Toast.makeText(itemView.context, "Error de servidor", Toast.LENGTH_SHORT).show()
                    }
                }
                val intent = Intent(itemView.context, HomeMantenimiento::class.java)
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
