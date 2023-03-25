package com.example.safegear

import androidx.appcompat.app.AppCompatActivity
import com.example.safegear.databinding.ActivityEditarVehiculoBinding
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.safegear.model.SharedApp
import com.example.safegear.model.UserResponse
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditarVehiculo : AppCompatActivity() {
    lateinit var binding: ActivityEditarVehiculoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEditarSOATEditarVehiculo.setOnClickListener {
            EditarSOAT(
            ).show(supportFragmentManager, "dialog")
        }

        binding.btnEditarTECNOEditarVehiculo.setOnClickListener {
            EditarTECNO(
            ).show(supportFragmentManager, "dialog")
        }
    }

    private fun getVehicle(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).getVehicleById(id)
            val dataVehicle = call.body()
            runOnUiThread {
                if (dataVehicle?.vehicle_id == null) {
                    showErrorDialog("Error al cargar el vehiculo")
                }
                binding.
            }
        }
    }

    private fun showErrorDialog(msg: String) {
        Toast.makeText(this, "Ha ocurrido un error "+msg, Toast.LENGTH_SHORT).show()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getString(R.string.url_base))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}