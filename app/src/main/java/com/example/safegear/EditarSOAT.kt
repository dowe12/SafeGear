package com.example.safegear

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.safegear.databinding.ActivityEditarSoatBinding
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import android.os.Bundle
import androidx.core.view.get
import java.util.*

class EditarSOAT(): DialogFragment() {

    private lateinit var binding : ActivityEditarSoatBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ActivityEditarSoatBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = builder.create()
        val inicio_editarsoat = binding.edtFechaInicioEditarSOAT
        val fin_editarsoat = binding.edtFechaFinEditarSOAT

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val datePickerDialog_inicio = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            // Aquí se hace lo que quiera con la fecha seleccionada amista
            val selectedDate_inicio = " $dayOfMonth/${month + 1}/$year"
            val edtSOATinit = binding.edtFechaInicioEditarSOAT.setText(selectedDate_inicio)
        }, year, month, day)

        val datePickerDialog_fin = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            // Aquí se hace lo que quiera con la fecha seleccionada amista
            val selectedDate_fin = " $dayOfMonth/${month + 1}/$year"
            val edtSOATfin = binding.edtFechaFinEditarSOAT.setText(selectedDate_fin)
        }, year, month, day)

        inicio_editarsoat.setOnClickListener {
            datePickerDialog_inicio.show()
        }

        fin_editarsoat.setOnClickListener {
            datePickerDialog_fin.show()
        }

        binding.btnEditarSOAT.setOnClickListener {
        }

        return dialog
    }
}