package com.example.safegear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safegear.adapter.MantenimientoAdapter
import com.example.safegear.databinding.ActivityHomeMantenimientoBinding
import com.example.safegear.model.MantenimientoModel
import com.example.safegear.model.SharedApp
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeMantenimiento : AppCompatActivity() {
    private lateinit var binding: ActivityHomeMantenimientoBinding
    private lateinit var adapter: MantenimientoAdapter
    private lateinit var mantenimientoList: List<MantenimientoModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_mantenimiento)

        val bundle = intent.extras
        val vehicleId = bundle?.getString("vehicleId")
        val placaId = bundle?.getString("placaId")


        binding = ActivityHomeMantenimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMaintenancebyVehicleId(SharedApp.prefs.id.toInt())

        binding.btnRegistrarMantenimiento.setOnClickListener{
            val intent = Intent(binding.root.context, RegistroMantenimientoActivity::class.java)
            intent.putExtra("vehicleId", vehicleId )
            intent.putExtra("placaId", placaId )
            startActivity(intent)
            finish()
        }
    }

    private fun initRecyclerView(mantenimientoList:List<MantenimientoModel>) {
        adapter = MantenimientoAdapter(mantenimientoList)
        binding.lstMantenimiento.layoutManager = LinearLayoutManager(this)
        binding.lstMantenimiento.adapter = adapter
    }

    private fun getMaintenancebyVehicleId(vehicleId:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getMaintenancesByVehicleId(vehicleId)
            val dataMaintenance = call.body()
            runOnUiThread {
                if (dataMaintenance != null) {
                    if (dataMaintenance.isEmpty()) {
                        showErrorDialog("Agrega un mantenimiento")
                    }
                    else
                    {
                        mantenimientoList = dataMaintenance
                        initRecyclerView(mantenimientoList)
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