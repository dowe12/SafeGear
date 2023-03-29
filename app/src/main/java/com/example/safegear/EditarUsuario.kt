package com.example.safegear

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safegear.databinding.ActivityEditarUsuarioBinding
import com.example.safegear.model.UsuarioModel
import com.example.safegear.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EditarUsuario : AppCompatActivity() {
    lateinit var binding: ActivityEditarUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val userId = bundle?.getString("userId")
        //Log.d("usuario id", userId.toString())

        if (userId != null) {
            getUser(userId.toInt())
        }

        binding.btnEditarUsuario.setOnClickListener {
            if (userId != null) {
                editUser(userId.toInt())
            }
        }

        binding.btnCancelarEditarUsuario.setOnClickListener{
            goCancelarEditar()
        }
    }

    private fun getUser(Id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).getUserById(Id)
            val dataUser = call.body()
            runOnUiThread {
                if (dataUser == null) {
                    showErrorDialog("Error al cargar el usuario")
                } else {
                    var rol = 0
                    when (dataUser.rol_id) {
                        "Usuario" -> {
                            rol = 1
                        }
                        "Mecánico" -> {
                            rol = 2
                        }
                    }

                    binding.edtEditarUsuarioContrasenia.setText(dataUser.contrasenia)
                    Log.d("data user contrasenia",dataUser.contrasenia.toString())
                    binding.edtEditarUsuarioNombres.setText(dataUser.nombre)
                    binding.edtEditarUsuarioApellidos.setText(dataUser.apellido)
                    binding.edtEditarUsuarioNumeroTelefono.setText(dataUser.telefono)
                    binding.edtEditarUsuarioNumeroIdentificacion.setText(dataUser.identificacion)
                    binding.edtEditarUsuarioCorreo.setText(dataUser.correo)
                }
            }
        }
    }

    private fun editUser(userId: Int) {
        val rol_id                       = "Usuario"
        val nombre                       = binding.edtEditarUsuarioNombres.text.toString()
        val apellido                     = binding.edtEditarUsuarioApellidos.text.toString()
        val identificacion               = binding.edtEditarUsuarioNumeroIdentificacion.text.toString()
        val telefono                     = binding.edtEditarUsuarioNumeroTelefono.text.toString()
        val correo                       = binding.edtEditarUsuarioCorreo.text.toString()
        val contrasenia                  = binding.edtEditarUsuarioContrasenia.text.toString()
        Log.d("contrasenia", contrasenia.toString())

        if (nombre.isEmpty() ||
            apellido.isEmpty() ||
            identificacion.isEmpty() ||
            telefono.isEmpty() ||
            correo.isEmpty()

        ) {
            return showDialog("Por favor ingrese todos los campos!")
        }

        CoroutineScope(Dispatchers.IO).launch {
            val user = UsuarioModel(
                "",
                userId.toString(),
                rol_id,
                nombre,
                apellido,
                telefono,
                identificacion,
                correo,
                contrasenia

            )
            Log.d("usuario id",userId.toString())
            Log.d("nombre",nombre)
            Log.d("apellido",apellido)
            Log.d("telefono",telefono)
            Log.d("identificacion",identificacion)
            Log.d("correo",correo)
            Log.d("contraseña",contrasenia)
            Log.d("rol",rol_id)
            val call = getRetrofit().create(APIService::class.java).userUpdate(userId, user)
            val dataUser = call.body()
            runOnUiThread {
                when (dataUser?.status) {
                    "success" -> {
                        showDialog("Usuario Editado con éxito!")
                        val intent = Intent(binding.root.context, HomeUsuario::class.java)
                        startActivity(intent)
                    }
                    "invalid" -> {
                        showErrorDialog(dataUser.message.toString())
                    }
                    else -> {
                        Log.e("API", "" + call)
                        showErrorDialog("")
                    }
                }

            }
        }
    }

    /*private fun showConfirmationMessage(userId: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).userDelete(userId.toInt())
            val dataUser = call.body()
            binding.root.handler.post{
                when (dataUser?.status) {
                    "success" -> {
                        Toast.makeText(itemView.context, "El usuario ha sido eliminado", Toast.LENGTH_SHORT).show()
                    }
                    "invalid" -> {
                        Toast.makeText(itemView.context, "Error al eliminar", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Log.e("API", "" + call)
                        Toast.makeText(itemView.context, "Error de servidor", Toast.LENGTH_SHORT).show()
                    }
                }
                val intent = Intent(itemView.context, HomeVehiculo::class.java)
                itemView.context.startActivity(intent)
                (context as Activity).finish()
            }
        }
    } */

    private fun goCancelarEditar(){
        val i= Intent(this,HomeUsuario::class.java)
        startActivity(i)
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