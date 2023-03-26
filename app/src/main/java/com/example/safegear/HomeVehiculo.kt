package com.example.safegear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safegear.adapter.VehiculoAdapter
import com.example.safegear.databinding.ActivityHomeVehiculoBinding
import com.example.safegear.model.SharedApp
import com.example.safegear.model.VehiculoModel
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeVehiculo : AppCompatActivity() {
    private lateinit var binding: ActivityHomeVehiculoBinding
    private lateinit var adapter: VehiculoAdapter
    private lateinit var vehiculoList: List<VehiculoModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_vehiculo)
        binding = ActivityHomeVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getVehiclesByUserId(SharedApp.prefs.id.toInt())

        binding.btnRegistrarVehiculo.setOnClickListener {
            val intent = Intent(binding.root.context, RegistroVehiculo::class.java)
            startActivity(intent)
        }

    }

    private fun initRecyclerView(vehiculoList:List<VehiculoModel>) {
        adapter = VehiculoAdapter(vehiculoList)
        binding.lstVehiculo.layoutManager = LinearLayoutManager(this)
        binding.lstVehiculo.adapter = adapter
    }

    private fun getVehiclesByUserId(userId:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).getVehiclesByUserId(userId)
            val dataVehicles = call.body()
            runOnUiThread {
                if (dataVehicles != null) {
                    if (dataVehicles.isEmpty()) {
                        showErrorDialog("Error al cargar el vehiculo")
                    }
                    else
                    {
                        vehiculoList = dataVehicles
                        initRecyclerView(vehiculoList)
                    }
                }
            }

        }
    }

    private fun showErrorDialog(msg: String) {
        Toast.makeText(this, "Ha ocurrido un error " + msg, Toast.LENGTH_SHORT).show()
    }

    private fun showDialog(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getString(R.string.url_base))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
