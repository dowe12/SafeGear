package com.example.safegear

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safegear.databinding.ActivityRegistroMantenimientoBinding
import java.util.*

private lateinit var binding : ActivityRegistroMantenimientoBinding

val calendar = Calendar.getInstance()
val year = calendar.get(Calendar.YEAR)
val month = calendar.get(Calendar.MONTH)
val day = calendar.get(Calendar.DAY_OF_MONTH)

class RegistroMantenimientoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroMantenimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtFecha.setOnClickListener{
            selectDate()
        }
    }

    private fun selectDate(){
        val datePickerDialog = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            binding.edtFecha.setText(selectedDate)
        }, year, month, day)
        datePickerDialog.show()
    }
}