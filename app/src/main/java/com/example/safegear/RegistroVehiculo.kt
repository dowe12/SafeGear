package com.example.safegear

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safegear.databinding.ActivityRegistroVehiculoBinding
import com.example.safegear.model.SharedApp
import com.example.safegear.model.VehiculoBodyRegister
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroVehiculo : AppCompatActivity() {

    lateinit var binding: ActivityRegistroVehiculoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val fecha = bundle?.getString("fechainicio_RegSOAT")

        binding.btnRegistrarVehiculo.setOnClickListener {
            registerVehicle()
        }

        binding.btnRegistrarSoat.setOnClickListener {
            RegistrarSOAT(
            ).show(supportFragmentManager, "dialog")
        }

        binding.btnRegistrarTecnomecanica.setOnClickListener {
            /*RegistrarTECNO(
            ).show(supportFragmentManager, "dialog")*/
            Toast.makeText(this, fecha, Toast.LENGTH_SHORT).show()
        }

    }

    private fun registerVehicle(){
        val user_id      = SharedApp.prefs.id
        val placa        = binding.edtRegistrarPlaca.text.toString()
        val marca        = binding.edtRegistrarMarca.text.toString()
        val modelo       = binding.edtRegistrarModelo.text.toString()
        val tipoVehiculo = binding.spinnerVehiculo.selectedItem.toString()
        val color        = binding.edtRegistrarColor.text.toString()
        val combustible  = binding.spinnerCombustible.selectedItem.toString()
        val cilindraje   = binding.edtRegistrarCilindraje.text.toString()
        val inicioSOAT   = "12/12/2023"
        val finSOAT      = "12/12/2023"
        val inicioTecno  = "12/12/2023"
        val finTecno     = "12/12/2023"

        if (placa.isEmpty()       ||
            marca.isEmpty()       ||
            color.isEmpty()       ||
            cilindraje.isEmpty()  ||
            inicioSOAT.isEmpty()  ||
            finSOAT.isEmpty()     ||
            inicioTecno.isEmpty() ||
            finTecno.isEmpty()
        ){
            return showDialog("Por favor ingrese todos los campos!")
        }

        CoroutineScope(Dispatchers.IO).launch {
            val vehicle = VehiculoBodyRegister(
                        user_id,
                        tipoVehiculo,
                        combustible,
                        placa,
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
                getRetrofit().create(APIService::class.java).vehicleRegister(vehicle)
            val dataVehicle = call.body()
            runOnUiThread {
                when (dataVehicle?.status) {
                    "success" -> {
                        clearInputs()
                        showDialog("Vehículo registrado con éxito!")
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

    private fun clearInputs(){
        binding.edtRegistrarPlaca.setText("")
        binding.edtRegistrarMarca.setText("")
        binding.edtRegistrarModelo.setText("")
        binding.edtRegistrarColor.setText("")
        binding.edtRegistrarCilindraje.setText("")
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