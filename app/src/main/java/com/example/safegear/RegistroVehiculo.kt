package com.example.safegear

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safegear.databinding.ActivityRegistroVehiculoBinding
import com.example.safegear.model.SharedApp
import com.example.safegear.model.VehiculoModel
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RegistroVehiculo : AppCompatActivity() {

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)


    lateinit var binding: ActivityRegistroVehiculoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarVehiculo.setOnClickListener {
            registerVehicle()
        }

        binding.edtFechaInicioRegistrarSOAT.setOnClickListener{
            SelectDateSOAT_ini()
        }
        binding.edtFechaFinRegistrarSOAT.setOnClickListener{
            SelectDateSOAT_fin()
        }
        binding.edtFechaInicioRegistrarTECNO.setOnClickListener{
            SelectDateTECNO_ini()
        }
        binding.edtFechaFinRegistrarTECNO.setOnClickListener{
            SelectDateTECNO_fin()
        }

    }

    private fun SelectDateSOAT_ini(){
        val datePickerDialogINICIO = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateINI = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaInicioRegistrarSOAT.setText(selectedDateINI)
        }, year, month, day)
        datePickerDialogINICIO.show()
    }

    private fun SelectDateSOAT_fin() {
        val datePickerDialogFIN = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
                val selectedDateFIN = "$dayOfMonth/${month + 1}/$year"
                binding.edtFechaFinRegistrarSOAT.setText(selectedDateFIN)
            }, year, month, day)
        datePickerDialogFIN.show()
    }

    private fun SelectDateTECNO_ini() {
        val datePickerDialogINICIO = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateINI = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaInicioRegistrarTECNO.setText(selectedDateINI)
        }, year, month, day)

        datePickerDialogINICIO.show()
    }

    private fun SelectDateTECNO_fin(){

        val datePickerDialogFIN = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateFIN = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaFinRegistrarTECNO.setText(selectedDateFIN)
        }, year, month, day)

        datePickerDialogFIN.show()
    }


    fun registerVehicle(){
        val user_id      = SharedApp.prefs.id
        val placa        = binding.edtRegistrarPlaca.text.toString()
        val marca        = binding.edtRegistrarMarca.text.toString()
        val modelo       = binding.edtRegistrarModelo.text.toString()
        val tipoVehiculo = binding.spinnerVehiculo.selectedItem.toString()
        val color        = binding.edtRegistrarColor.text.toString()
        val combustible  = binding.spinnerCombustible.selectedItem.toString()
        val cilindraje   = binding.edtRegistrarCilindraje.text.toString()
        Log.e("FECHA tecno date picker", binding.edtFechaInicioRegistrarTECNO.text.toString())
        val inicioSOAT   = binding.edtFechaInicioRegistrarTECNO.text.toString()
        val finSOAT      = binding.edtFechaInicioRegistrarTECNO.text.toString()
        val inicioTecno  = binding.edtFechaInicioRegistrarTECNO.text.toString()
        val finTecno     = binding.edtFechaInicioRegistrarTECNO.text.toString()

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
            val vehicle = VehiculoModel("status","id",
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
            Log.e("VEHICLE", ""+vehicle.toString());
            Log.e("VEHICLE", ""+binding.edtFechaFinRegistrarTECNO.text.toString());
            val call =
                getRetrofit().create(APIService::class.java).vehicleRegister(vehicle)
            val dataVehicle = call.body()
            Log.e("VEHICLE", ""+dataVehicle.toString());
            runOnUiThread {
                when (dataVehicle?.status) {
                    "success" -> {
                        clearInputs()
                        showDialog("Vehículo registrado con éxito!")
                        val intent = Intent(binding.root.context, HomeVehiculo::class.java)
                        startActivity(intent)
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