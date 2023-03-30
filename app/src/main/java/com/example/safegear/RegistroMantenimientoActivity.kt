package com.example.safegear

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.safegear.databinding.ActivityRegistroMantenimientoBinding
import com.example.safegear.model.MantenimientoModel
import com.example.safegear.model.SharedApp
import com.example.safegear.model.VehiculoModel
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class RegistroMantenimientoActivity : AppCompatActivity() {


    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    lateinit var binding : ActivityRegistroMantenimientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroMantenimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val vehicleId = bundle?.getString("vehicleId")
        val placaId = bundle?.getString("placaId")

        binding.tvPlaca.setText(placaId)

        binding.btnRegistrarMantenimiento.setOnClickListener {
            registerMaintenance(vehicleId.toString())
        }

        binding.edtFecha.setOnClickListener{
            selectDate()
        }
    }

    private fun selectDate(){
        val datePickerDialog = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            binding.edtFecha.setText(selectedDate)
        }, year, month, day)
        datePickerDialog.show()
    }

    fun registerMaintenance(vehicleId:String) {
        val vehicle         = vehicleId
        val fecha           = binding.edtFecha.text.toString()
        val titulo          = binding.edtTitulo.text.toString()
        val descripcion     = binding.edtDescripcion.text.toString()
        val precio          = binding.edtPrecio.text.toString()
        val nombreMecanico  = binding.edtNombreMecanico.text.toString()

        if (fecha.isEmpty()       ||
            titulo.isEmpty()       ||
            descripcion.isEmpty()       ||
            precio.isEmpty()  ||
            nombreMecanico.isEmpty()
        ){
            return showDialog("¡Por favor ingrese todos los campos!")
        }
        CoroutineScope(Dispatchers.IO).launch {
            val maintenance = MantenimientoModel(
                "status", "id",
                vehicle,
                titulo,
                descripcion,
                "url_foto",
                fecha,
                nombreMecanico,
                precio
            )
            val call =
                getRetrofit().create(APIService::class.java).maintenanceRegister(maintenance)
            val dataMaintenance = call.body()
            runOnUiThread {
                when (dataMaintenance?.status) {
                    "success" -> {
                        clearInputs()
                        showDialog("Mantenimiento registrado con éxito!")
                        val intent = Intent(binding.root.context, HomeVehiculo::class.java)
                        startActivity(intent)
                        finish()
                    }
                    "invalid" -> {
                        showErrorDialog(dataMaintenance.message.toString())
                    }
                    else -> {
                        Log.e("API", "" + call)
                        showErrorDialog("")
                    }
                }
            }
        }
    }

    private fun clearInputs(){
        binding.edtFecha.setText("")
        binding.edtTitulo.setText("")
        binding.edtDescripcion.setText("")
        binding.edtPrecio.setText("")
        binding.edtNombreMecanico.setText("")
    }

    private fun showDialog(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorDialog(msg: String) {
        Toast.makeText(this, "Ha ocurrido un error "+msg, Toast.LENGTH_SHORT).show()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getString(R.string.url_base))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
