package com.example.safegear

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import android.os.Bundle
import com.example.safegear.databinding.ActivityRegistrarTecnoBinding
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
        val inicioRegistrarTECNO = binding.edtFechaInicioRegistrarTECNO
        val finRegistrarTECNO = binding.edtFechaFinRegistrarTECNO

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val datePickerDialogINICIO = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateINI = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaInicioRegistrarTECNO.setText(selectedDateINI)
        }, year, month, day)

        val datePickerDialogFIN = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateFIN = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaFinRegistrarTECNO.setText(selectedDateFIN)
        }, year, month, day)

        inicioRegistrarTECNO.setOnClickListener {
            datePickerDialogINICIO.show()
        }

        finRegistrarTECNO.setOnClickListener {
            datePickerDialogFIN.show()
        }

        binding.btnRegistrarTECNO.setOnClickListener {
            val intent = Intent(binding.root.context, RegistroVehiculo::class.java)
            intent.putExtra("fechainicio_RegTECNO", binding.edtFechaInicioRegistrarTECNO.text.toString())
            intent.putExtra("fechafin_RegTECNO", binding.edtFechaFinRegistrarTECNO.text.toString())
            startActivity(intent)
        }

        return dialog
    }

}