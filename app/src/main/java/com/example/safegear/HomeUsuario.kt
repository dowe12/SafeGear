package com.example.safegear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safegear.databinding.ActivityHomeUsuarioBinding

class HomeUsuario : AppCompatActivity() {
    lateinit var binding: ActivityHomeUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVehiculosHomeU.setOnClickListener {
            val intent = Intent(binding.root.context, HomeVehiculo::class.java)
            startActivity(intent)
        }
    }
}