package com.example.safegear

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.safegear.databinding.ActivityRegistrarSoatBinding
import java.util.*

class RegistrarSOAT(): DialogFragment() {

    private lateinit var binding : ActivityRegistrarSoatBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ActivityRegistrarSoatBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        binding.btnRegistrarSOAT.setOnClickListener {



        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val inicio_soat = binding.tvFechaCRegistrarSOAT
        val datePickerDialog = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            // Aquí se hace lo que quiera con la fecha seleccionada amista
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            val edtSOATinit = binding.edtFechaInicioRegistrarSOAT.setText(selectedDate)

        }, year, month, day)

        inicio_soat.setOnClickListener {
            datePickerDialog.show()
        }

        return dialog


    }
}


