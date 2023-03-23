package com.example.safegear

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
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
        val inicioEditarSOAT = binding.edtFechaInicioEditarSOAT
        val finEditarSOAT = binding.edtFechaFinEditarSOAT

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val datePickerDialogINICIO = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateINI = " $dayOfMonth/${month + 1}/$year"
            binding.edtFechaInicioEditarSOAT.setText(selectedDateINI)
        }, year, month, day)

        val datePickerDialogFIN = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateFIN = " $dayOfMonth/${month + 1}/$year"
            binding.edtFechaFinEditarSOAT.setText(selectedDateFIN)
        }, year, month, day)

        inicioEditarSOAT.setOnClickListener {
            datePickerDialogINICIO.show()
        }

        finEditarSOAT.setOnClickListener {
            datePickerDialogFIN.show()
        }

        binding.btnEditarSOAT.setOnClickListener {
            val intent = Intent(binding.root.context, EditarVehiculo::class.java)
            intent.putExtra("fechainicio_EdtSOAT", binding.edtFechaInicioEditarSOAT.text.toString())
            intent.putExtra("fechafin_EdtSOAT", binding.edtFechaFinEditarSOAT.text.toString())
            startActivity(intent)
        }

        return dialog
    }
}