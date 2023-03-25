package com.example.safegear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.TextView
import com.example.safegear.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.safegear.model.UserResponse
import com.example.safegear.model.SharedApp
import com.example.safegear.network.APIService
import com.example.safegear.ui.mechanic.HomeMechanicActivity

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLoginIniciarSesion.setOnClickListener {
            var correo = binding.edtLoginCorreo.text.toString()
            var contrasenia = binding.edtLoginContrasenia.text.toString()
            val user = UserResponse(correo, contrasenia)
            Log.e("User",""+user.toString())
            postSignIn(user)
        }

        binding.tvRegistrarUsuario.setOnClickListener {
            val intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }
    }

    private fun postSignIn(user: UserResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).signIn(user)
            val dataUser = call.body()
            runOnUiThread {
                if (dataUser?.status == "success") {
                    val rol = dataUser.rol_id.toString()
                    SharedApp.prefs.id              = dataUser.id_user.toString()
                    SharedApp.prefs.rolId           = rol
                    SharedApp.prefs.name            = dataUser.nombre.toString()
                    SharedApp.prefs.lastname        = dataUser.apellido.toString()
                    SharedApp.prefs.identification  = dataUser.identificacion.toString()
                    SharedApp.prefs.jwt             = dataUser.jwt.toString()
                    Log.d("API funciona", "" + dataUser.id_user)
                    showMainMenu(rol)
                } else if (dataUser?.status == "invalid") {
                    showErrorDialog(dataUser.message.toString())
                } else {
                    Log.e("API", "" + call)
                    showErrorDialog("")
                }
            }
        }
    }

    private fun showErrorDialog(msg: String) {
        Toast.makeText(this, "Ha ocurrido un error "+msg, Toast.LENGTH_SHORT).show()
    }

    private fun showMainMenu(rol: String){
        when(rol){
            "1" -> {
                val i = Intent(this, HomeUsuario::class.java)
                startActivity(i)
                finish()
            }
            "2" -> {
                val i = Intent(this, HomeMechanicActivity::class.java)
                startActivity(i)
                finish()
            }
            else -> {
                showErrorDialog(":(")
            }
        }
    }

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(getString(R.string.url_base))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
