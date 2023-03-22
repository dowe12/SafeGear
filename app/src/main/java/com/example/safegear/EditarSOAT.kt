package com.example.safegear

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.safegear.databinding.ActivityEditarSoatBinding
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import android.os.Bundle

/*class EditarSOAT : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_soat)
    }
}*/

class EditarSOAT(): DialogFragment() {

    private lateinit var binding : ActivityEditarSoatBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ActivityEditarSoatBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)


        binding.btnEditarSOAT.setOnClickListener {
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}