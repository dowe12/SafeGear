package com.example.safegear

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.safegear.databinding.ActivityEditarVehiculoBinding
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.safegear.model.SharedApp

import com.example.safegear.model.UserResponse

import com.example.safegear.model.VehicleBodyEdit
import com.example.safegear.model.VehiculoModel

import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.*


class EditarVehiculo : AppCompatActivity() {


    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)


    lateinit var binding: ActivityEditarVehiculoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val vehicleId = bundle?.getString("vehicleId")

        if (vehicleId != null) {
            getVehicle(vehicleId.toInt())

        }

        binding.btnEditarVehiculo.setOnClickListener {
            if (vehicleId != null) {
                editVehicle(vehicleId.toInt())
            }
        }

        binding.edtFechaInicioEditarTECNO.setOnClickListener {
            SelectDateSOAT_ini()
        }
        binding.edtFechaFinEditarSOAT.setOnClickListener{
            SelectDateSOAT_fin()
        }
        binding.edtFechaInicioEditarTECNO.setOnClickListener{
            SelectDateTECNO_ini()
        }
        binding.edtFechaFinEditarTECNO.setOnClickListener{
            SelectDateTECNO_fin()
        }
    }

    private fun getVehicle(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).getVehicleById(id)
            val dataVehicle = call.body()
            runOnUiThread {
                if (dataVehicle?.vehicle_id == null) {
                    showErrorDialog("Error al cargar el vehiculo")
                } else {
                    var claseVehiculo = 1
                    var combustible   = 1
                    when(dataVehicle.clase_vehiculo){
                        "Moto" -> {
                            claseVehiculo = 1
                        }
                        "Carro" -> {
                            claseVehiculo = 2
                        }
                        "Bus" -> {
                            claseVehiculo = 3
                        }
                        "Motocarro" -> {
                            claseVehiculo = 4
                        }
                        "Cuatrimoto" -> {
                            claseVehiculo = 5
                        }
                    }
                    when(dataVehicle.combustible){
                        "Gasolina corriente" -> {
                            combustible = 1
                        }
                        "Gasolina premium" -> {
                            combustible = 2
                        }
                        "Diesel" -> {
                            combustible = 3
                        }
                    }

                    binding.tvPlacaEditarVehiculo.text = dataVehicle.placa
                    binding.edtMarcaVehiculoEditarVehiculo.setText(dataVehicle.marca)
                    binding.edtModeloVehiculoEditarVehiculo.setText(dataVehicle.modelo)
                    binding.spnTipoVehiculoEditarVehiculo.setSelection(claseVehiculo)
                    binding.edtColorVehiculoEditarVehiculo.setText(dataVehicle.color)
                    binding.spnCombustibleEditarVehiculo.setSelection(combustible)
                    binding.edtCilindrajeEditarVehiculo.setText(dataVehicle.cilindraje)
                    binding.edtFechaInicioEditarSOAT.setText(dataVehicle.fecha_inicio_SOAT)
                    binding.edtFechaFinEditarSOAT.setText(dataVehicle.fecha_fin_SOAT)
                    binding.edtFechaInicioEditarTECNO.setText(dataVehicle.fecha_inicio_tecno)
                    binding.edtFechaFinEditarTECNO.setText(dataVehicle.fecha_fin_tecno)
                }
            }
        }
    }

    private fun SelectDateSOAT_ini(){

        val datePickerDialogINICIO = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateINI = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaInicioEditarSOAT.setText(selectedDateINI)
        }, year, month, day)
        datePickerDialogINICIO.show()
        return
    }

    private fun SelectDateSOAT_fin() {
        val datePickerDialogFIN = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateFIN = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaFinEditarSOAT.setText(selectedDateFIN)
        }, year, month, day)
        datePickerDialogFIN.show()
        return
    }

    private fun SelectDateTECNO_ini() {
        val datePickerDialogINICIO = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateINI = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaInicioEditarTECNO.setText(selectedDateINI)
        }, year, month, day)

        datePickerDialogINICIO.show()
        return
    }

    private fun SelectDateTECNO_fin(){

        val datePickerDialogFIN = DatePickerDialog(binding.root.context, { _, year, month, dayOfMonth ->
            val selectedDateFIN = "$dayOfMonth/${month + 1}/$year"
            binding.edtFechaFinEditarTECNO.setText(selectedDateFIN)
        }, year, month, day)

        datePickerDialogFIN.show()

        return
    }
    private fun editVehicle(vehicleId: Int) {
        val user_id         = SharedApp.prefs.id
        val placa           = binding.tvPlacaEditarVehiculo.text.toString()
        val marca           = binding.edtMarcaVehiculoEditarVehiculo.text.toString()
        val modelo          = binding.edtModeloVehiculoEditarVehiculo.text.toString()
        val tipoVehiculo    = binding.spnTipoVehiculoEditarVehiculo.selectedItem.toString()
        val color           = binding.edtColorVehiculoEditarVehiculo.text.toString()
        val combustible     = binding.spnCombustibleEditarVehiculo.selectedItem.toString()
        val cilindraje      = binding.edtCilindrajeEditarVehiculo.text.toString()
        val inicioSOAT      = binding.edtFechaInicioEditarSOAT.text.toString()
        val finSOAT         = binding.edtFechaFinEditarSOAT.text.toString()
        val inicioTecno     = binding.edtFechaInicioEditarTECNO.text.toString()
        val finTecno        = binding.edtFechaFinEditarTECNO.text.toString()

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
            val vehicle = VehiculoModel(
                "",
                vehicleId.toString(),
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
                getRetrofit().create(APIService::class.java).vehicleUpdate(vehicleId, vehicle)
            val dataVehicle = call.body()
            runOnUiThread {
                when (dataVehicle?.status) {
                    "success" -> {
                        showDialog("Vehículo Editado con éxito!")
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
