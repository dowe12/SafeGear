package com.example.safegear

import androidx.appcompat.app.AppCompatActivity
import com.example.safegear.databinding.ActivityEditarVehiculoBinding
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.safegear.model.SharedApp
import com.example.safegear.model.VehicleBodyEdit
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditarVehiculo : AppCompatActivity() {
    lateinit var binding: ActivityEditarVehiculoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEditarVehiculo.setOnClickListener {
            editVehicle()
        }

        binding.btnEditarSOATEditarVehiculo.setOnClickListener {
            EditarSOAT(
            ).show(supportFragmentManager, "dialog")
        }

        binding.btnEditarTECNOEditarVehiculo.setOnClickListener {
            EditarTECNO(
            ).show(supportFragmentManager, "dialog")
        }
    }

    private fun editVehicle() {
        val bundle = intent.extras
        val user_id = SharedApp.prefs.id
        //val placa = binding..text.toString()
        val marca = binding.edtMarcaVehiculoEditarVehiculo.text.toString()
        val modelo = binding.edtModeloVehiculoEditarVehiculo.text.toString()
        val tipoVehiculo = binding.spnTipoVehiculoEditarVehiculo.selectedItem.toString()
        val color = binding.edtColorVehiculoEditarVehiculo.text.toString()
        val combustible = binding.spnCombustibleEditarVehiculo.selectedItem.toString()
        val cilindraje = binding.edtCilindrajeEditarVehiculo.text.toString()
        val inicioSOAT = bundle?.getString("fechainicio_RegSOAT").toString()
        val finSOAT = bundle?.getString("fechafin_RegSOAT").toString()
        val inicioTecno = bundle?.getString("fechainicio_RegTECNO").toString()
        val finTecno = bundle?.getString("fechafin_RegTECNO").toString()

        if (//placa.isEmpty() ||
            marca.isEmpty() ||
            color.isEmpty() ||
            cilindraje.isEmpty() ||
            inicioSOAT.isEmpty() ||
            finSOAT.isEmpty() ||
            inicioTecno.isEmpty() ||
            finTecno.isEmpty()
        ) {
            return showDialog("Por favor ingrese todos los campos!")
        }

        CoroutineScope(Dispatchers.IO).launch {
            val vehicle = VehicleBodyEdit(
                user_id,
                tipoVehiculo,
                combustible,
                //placa,
                marca,
                modelo,
                color,
                cilindraje,
                inicioSOAT,
                finSOAT,
                inicioTecno,
                finTecno
            )
            val call =
                getRetrofit().create(APIService::class.java).vehicleEdit(vehicle)
            val dataVehicle = call.body()
            runOnUiThread {
                when (dataVehicle?.status) {
                    "success" -> {
                        clearInputs()
                        showDialog("Vehículo Editado con éxito!")
                    }
                    "invalid" -> {
                        showErrorDialog(dataVehicle.message.toString())
                    }
                    else -> {
                        Log.e("API", "" + call)
                        showErrorDialog("")
                    }
                }
            }
        }
    }


    private fun clearInputs() {
        //binding.edtRegistrarPlaca.setText("")
        binding.edtMarcaVehiculoEditarVehiculo.setText("")
        binding.edtModeloVehiculoEditarVehiculo.setText("")
        binding.edtColorVehiculoEditarVehiculo.setText("")
        binding.edtCilindrajeEditarVehiculo.setText("")
    }

    private fun showDialog(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorDialog(msg: String) {
        Toast.makeText(this, "Ha ocurrido un error " + msg, Toast.LENGTH_SHORT).show()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getString(R.string.url_base))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}