package com.example.safegear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safegear.databinding.ActivityEditarMantenimientoBinding
import com.example.safegear.databinding.ActivityRegistroMantenimientoBinding
import java.util.*
import android.app.DatePickerDialog
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.safegear.model.SharedApp
import com.example.safegear.model.VehiculoModel
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private lateinit var binding : ActivityEditarMantenimientoBinding

val calendar = Calendar.getInstance()
val year = calendar.get(Calendar.YEAR)
val month = calendar.get(Calendar.MONTH)
val day = calendar.get(Calendar.DAY_OF_MONTH)


class EditarMantenimiento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_mantenimiento)
        binding = ActivityEditarMantenimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtEditarMantenimientoFecha.setOnClickListener{
            selectDate()
        }

    }
}

private fun selectDate(){
    val datePickerDialog = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
        val selectedDate = "$year-${month + 1}-$dayOfMonth"
        binding.edtEditarMantenimientoFecha.setText(selectedDate)
    }, year, month, day)
    datePickerDialog.show()
}






