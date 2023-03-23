package com.example.safegear

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.safegear.databinding.ActivityEditarTecnoBinding
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.safegear.databinding.ActivityRegistrarTecnoBinding
import com.example.safegear.databinding.ActivityRegistroVehiculoBinding
import java.util.*

class RegistrarTECNO(): DialogFragment() {

    private lateinit var binding : ActivityRegistrarTecnoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ActivityRegistrarTecnoBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = builder.create()
        val inicio_registrarsoat = binding.edtFechaInicioRegistrarTECNO
        val fin_registrarsoat = binding.edtFechaFinRegistrarTECNO

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val datePickerDialog_inicio = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            // Aquí se hace lo que quiera con la fecha seleccionada amista
            val selectedDate_inicio = " $dayOfMonth/${month + 1}/$year"
            val edtRegTECNOinit = binding.edtFechaInicioRegistrarTECNO.setText(selectedDate_inicio)
        }, year, month, day)

        val datePickerDialog_fin = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            // Aquí se hace lo que quiera con la fecha seleccionada amista
            val selectedDate_fin = " $dayOfMonth/${month + 1}/$year"
            val edtRegTECNOfin = binding.edtFechaFinRegistrarTECNO.setText(selectedDate_fin)
        }, year, month, day)

        inicio_registrarsoat.setOnClickListener {
            datePickerDialog_inicio.show()
        }

        fin_registrarsoat.setOnClickListener {
            datePickerDialog_fin.show()
        }

        binding.btnRegistrarTECNO.setOnClickListener {
        }

        return dialog
    }

}