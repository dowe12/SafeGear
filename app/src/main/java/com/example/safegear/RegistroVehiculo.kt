package com.example.safegear


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safegear.databinding.ActivityRegistroVehiculoBinding


class RegistroVehiculo : AppCompatActivity() {

    lateinit var binding: ActivityRegistroVehiculoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }




}