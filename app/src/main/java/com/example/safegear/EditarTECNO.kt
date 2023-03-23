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
import java.util.*

class EditarTECNO(): DialogFragment() {

    private lateinit var binding : ActivityEditarTecnoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ActivityEditarTecnoBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = builder.create()
        val inicio_editartecno = binding.edtFechaInicioEditarTECNO
        val fin_editartecno = binding.edtFechaFinEditarTECNO

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val datePickerDialog_inicio = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            // Aquí se hace lo que quiera con la fecha seleccionada amista
            val selectedDate_inicio = " $dayOfMonth/${month + 1}/$year"
            val edtTECNOinit = binding.edtFechaInicioEditarTECNO.setText(selectedDate_inicio)
        }, year, month, day)

        val datePickerDialog_fin = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            // Aquí se hace lo que quiera con la fecha seleccionada amista
            val selectedDate_fin = " $dayOfMonth/${month + 1}/$year"
            val edtTECNOfin = binding.edtFechaFinEditarTECNO.setText(selectedDate_fin)

        }, year, month, day)

        inicio_editartecno.setOnClickListener {
            datePickerDialog_inicio.show()
        }

        fin_editartecno.setOnClickListener {
            datePickerDialog_fin.show()
        }

        binding.btnEditarTECNO.setOnClickListener {
        }

        return dialog
    }
}