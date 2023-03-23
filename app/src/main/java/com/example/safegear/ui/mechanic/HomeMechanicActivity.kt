package com.example.safegear.ui.mechanic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safegear.databinding.ActivityHomeMechanicBinding
import com.example.safegear.databinding.ActivityLoginBinding
import com.example.safegear.databinding.ActivityRegistroUsuarioBinding

class HomeMechanicActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeMechanicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeMechanicBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}