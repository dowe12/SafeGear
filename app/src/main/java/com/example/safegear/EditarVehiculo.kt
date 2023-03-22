package com.example.safegear

import androidx.appcompat.app.AppCompatActivity
import com.example.safegear.databinding.ActivityEditarVehiculoBinding
import android.os.Bundle

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
}