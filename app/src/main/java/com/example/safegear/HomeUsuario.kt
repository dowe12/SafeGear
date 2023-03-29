package com.example.safegear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.safegear.databinding.ActivityHomeUsuarioBinding
import com.example.safegear.model.SharedApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.safegear.model.UsuarioModel

class HomeUsuario : AppCompatActivity() {
    lateinit var binding: ActivityHomeUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idusuario =  SharedApp.prefs.id.toInt();
        Log.d("usuario id", SharedApp.prefs.id.toInt().toString())

        binding.btnPerfilHomeU.setOnClickListener {
            val intent = Intent(binding.root.context, EditarUsuario::class.java)
            intent.putExtra("userId",idusuario.toString())
            startActivity(intent)
        }
        binding.btnVehiculosHomeU.setOnClickListener {
            val intent = Intent(binding.root.context, HomeVehiculo::class.java)
            startActivity(intent)
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getString(R.string.url_base))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}