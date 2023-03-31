package com.example.safegear

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.safegear.databinding.ActivityEditarMantenimientoBinding
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.safegear.model.SharedApp
import com.example.safegear.model.MantenimientoModel
import com.example.safegear.model.VehiculoModel
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.*

class EditarMantenimiento : AppCompatActivity() {

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    private lateinit var binding: ActivityEditarMantenimientoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarMantenimientoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_editar_mantenimiento)
        setContentView(binding.root)
        val bundle = intent.extras
        val maintenanceId = bundle?.getString("maintenanceId")

        Log.d("maintenanceId",maintenanceId.toString())
        if (maintenanceId != null) {
            getMantenimiento(maintenanceId.toInt())
        }

        binding.btnEditarMantenimiento.setOnClickListener{
            if(maintenanceId != null){
                editMantenimiento(maintenanceId.toInt())
            }
        }

        binding.edtEditarMantenimientoFecha.setOnClickListener {
            selectDate()
        }

    }

    private fun getMantenimiento(Id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).getMaintenanceById(Id)
            val dataMantenimiento = call.body()
            runOnUiThread{
                if(dataMantenimiento == null){
                    showErrorDialog("Error al cargar el mantenimiento")
                } else {
                    binding.edtEditarMantenimientoTitulo.setText(dataMantenimiento.titulo)
                    binding.edtEditarMantenimientoDescripcion.setText(dataMantenimiento.descripcion)
                    binding.btnEditarMantenimientoFoto.setText(dataMantenimiento.url_foto)
                    binding.edtEditarMantenimientoFecha.setText(dataMantenimiento.fecha_mantenimiento)
                    binding.edtEditarMantenimientoNombreMecanico.setText(dataMantenimiento.nombre_mecanico)
                    binding.edtEditarMantenimientoPrecio.setText(dataMantenimiento.precio)
                }
            }
        }
    }
    private fun selectDate() {
        val datePickerDialog =
            DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
                val selectedDate = "$year-${month + 1}-$dayOfMonth"
                binding.edtEditarMantenimientoFecha.setText(selectedDate)
            }, year, month, day)
        datePickerDialog.show()
    }

    private fun editMantenimiento(maintenanceId:Int) {
        val titulo           = binding.edtEditarMantenimientoTitulo.text.toString()
        val descripcion          = binding.edtEditarMantenimientoDescripcion.text.toString()
        val urlfoto    = binding.btnEditarMantenimientoFoto.text.toString()
        val fecha           = binding.edtEditarMantenimientoFecha.text.toString()
        val nombre_mecanico     = binding.edtEditarMantenimientoNombreMecanico.text.toString()
        val precio      = binding.edtEditarMantenimientoPrecio.text.toString()

        if (titulo.isEmpty() ||
            descripcion.isEmpty() ||
            urlfoto.isEmpty() ||
            fecha.isEmpty() ||
            nombre_mecanico.isEmpty() ||
            precio.isEmpty()
        ) {
            return showDialog("Por favor ingrese todos los campos!")
        }

        CoroutineScope(Dispatchers.IO).launch {
            val maintenance = MantenimientoModel(
                "",
                maintenanceId.toString(),
                "",
                titulo,
                descripcion,
                urlfoto,
                fecha,
                nombre_mecanico,
                precio
            )
            Log.d("mantenimiento",maintenance.toString())
            val call = getRetrofit().create(APIService::class.java).maintenanceUpdate(maintenanceId,maintenance)
            val dataMantenimiento = call.body()
            runOnUiThread {
                when (dataMantenimiento?.status) {
                    "success" -> {
                        showDialog("Mantenimiento Editado con éxito!")
                        val intent = Intent(binding.root.context, HomeMantenimiento::class.java)
                        //intent.putExtra("vehicleId", maintenance.vehiculo_id)
                        startActivity(intent)
                        finish()
                    }
                    "invalid" -> {
                        showErrorDialog(dataMantenimiento.message.toString())
                    }
                    else -> {
                        Log.e("API", "" + call)
                        showErrorDialog("")
                    }
                }

            }
        }
    }

    private fun showErrorDialog(msg: String) {
        Toast.makeText(this, "Ha ocurrido un error " + msg, Toast.LENGTH_SHORT).show()
    }

    private fun showDialog(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getString(R.string.url_base))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}






