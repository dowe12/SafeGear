package com.example.safegear

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.safegear.databinding.ActivityRegistrarSoatBinding
import java.util.*

class RegistrarSOAT(): DialogFragment() {

    private lateinit var binding : ActivityRegistrarSoatBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ActivityRegistrarSoatBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = builder.create()
        val inicioRegistrarSOAT = binding.edtFechaInicioRegistrarSOAT
        val finRegistrarSOAT = binding.edtFechaFinRegistrarSOAT

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val datePickerDialogINICIO = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateINI = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaInicioRegistrarSOAT.setText(selectedDateINI)
        }, year, month, day)

        val datePickerDialogFIN = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateFIN = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaFinRegistrarSOAT.setText(selectedDateFIN)
        }, year, month, day)

        inicioRegistrarSOAT.setOnClickListener {
            datePickerDialogINICIO.show()
        }

        finRegistrarSOAT.setOnClickListener {
            datePickerDialogFIN.show()
        }

        binding.btnRegistrarSOAT.setOnClickListener {
            val intent = Intent(binding.root.context, RegistroVehiculo::class.java)
            intent.putExtra("fechainicio_RegSOAT", binding.edtFechaInicioRegistrarSOAT.text.toString())
            intent.putExtra("fechafin_RegSOAT", binding.edtFechaFinRegistrarSOAT.text.toString())
            startActivity(intent)
        }

        return dialog
    }
}


