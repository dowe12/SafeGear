package com.example.safegear

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


        binding.btnRegistrarTECNO.setOnClickListener {


        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}