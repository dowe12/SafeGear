package com.example.safegear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safegear.adapter.VehiculoAdapter
import com.example.safegear.databinding.ActivityHomeMantenimientoBinding
import com.example.safegear.model.MantenimientoModel
import com.example.safegear.model.VehiculoModel

class HomeMantenimiento : AppCompatActivity() {
    private lateinit var binding: ActivityHomeMantenimientoBinding
    private lateinit var adapter: VehiculoAdapter
    private lateinit var mantenimientoList: List<MantenimientoModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_mantenimiento)

        binding = ActivityHomeMantenimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarMantenimiento.setOnClickListener{
            val intent = Intent(binding.root.context, RegistroMantenimientoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}