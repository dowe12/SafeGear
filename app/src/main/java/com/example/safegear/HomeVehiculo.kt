package com.example.safegear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safegear.adapter.VehiculoAdapter
import com.example.safegear.databinding.ActivityHomeVehiculoBinding
import com.example.safegear.model.VehiculoModel

class HomeVehiculo : AppCompatActivity() {
    private lateinit var binding: ActivityHomeVehiculoBinding
    private lateinit var adapter: VehiculoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_vehiculo)
        binding = ActivityHomeVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarVehiculo.setOnClickListener {
            val intent = Intent(binding.root.context, RegistroVehiculo::class.java)
            startActivity(intent)
        }

        val vehiculos = listOf(
            VehiculoModel("y","y","y","y","y","AAA-123","y","y",
            "y","y","y","y","y","y"),
            VehiculoModel("y","y","y","y","y","BBB-234","y","y",
                "y","y","y","y","y","y"),
            VehiculoModel("y","y","y","y","y","CCC-456","y","y",
                "y","y","y","y","y","y")
        )

        initRecyclerView(vehiculos)
    }

    private fun initRecyclerView(vehiculoList:List<VehiculoModel>) {
        adapter = VehiculoAdapter(vehiculoList)
        binding.lstVehiculo.layoutManager = LinearLayoutManager(this)
        binding.lstVehiculo.adapter = adapter
    }
}