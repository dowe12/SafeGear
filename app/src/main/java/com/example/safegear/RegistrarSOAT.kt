package com.example.safegear

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
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
        val dialog = builder.create()
        val inicio_registrarsoat = binding.edtFechaInicioRegistrarSOAT
        val fin_registrarsoat = binding.edtFechaFinRegistrarSOAT

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val datePickerDialog_inicio = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            // Aquí se hace lo que quiera con la fecha seleccionada amista
            val selectedDate_inicio = " $dayOfMonth/${month + 1}/$year"
            val edtRegSOATinit = binding.edtFechaInicioRegistrarSOAT.setText(selectedDate_inicio)
        }, year, month, day)

        val datePickerDialog_fin = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            // Aquí se hace lo que quiera con la fecha seleccionada amista
            val selectedDate_fin = " $dayOfMonth/${month + 1}/$year"
            val edtRegSOATfin = binding.edtFechaFinRegistrarSOAT.setText(selectedDate_fin)
        }, year, month, day)

        inicio_registrarsoat.setOnClickListener {
            datePickerDialog_inicio.show()
        }

        fin_registrarsoat.setOnClickListener {
            datePickerDialog_fin.show()
        }

        binding.btnRegistrarSOAT.setOnClickListener {
            val intent1 = Intent(binding.root.context, RegistroVehiculo::class.java)
            intent1.putExtra("fechainicio_RegSOAT", binding.edtFechaInicioRegistrarSOAT.toString())
            startActivity(intent1)
        }

        return dialog
    }
}


