package com.example.safegear

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
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
        val inicioEditarTECNO = binding.edtFechaInicioEditarTECNO
        val finEditarTECNO = binding.edtFechaFinEditarTECNO

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val datePickerDialogINICIO = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateINI = " $dayOfMonth/${month + 1}/$year"
            binding.edtFechaInicioEditarTECNO.setText(selectedDateINI)
        }, year, month, day)

        val datePickerDialogFIN = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateFIN = " $dayOfMonth/${month + 1}/$year"
            binding.edtFechaFinEditarTECNO.setText(selectedDateFIN)

        }, year, month, day)

        inicioEditarTECNO.setOnClickListener {
            datePickerDialogINICIO.show()
        }

        finEditarTECNO.setOnClickListener {
            datePickerDialogFIN.show()
        }

        binding.btnEditarTECNO.setOnClickListener {
            val intent = Intent(binding.root.context, EditarVehiculo::class.java)
            intent.putExtra("fechainicio_EdtTECNO", binding.edtFechaInicioEditarTECNO.text.toString())
            intent.putExtra("fechafin_EdtTECNO", binding.edtFechaFinEditarTECNO.text.toString())
            startActivity(intent)
        }

        return dialog
    }
}