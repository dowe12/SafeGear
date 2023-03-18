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

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLoginIniciarSesion.setOnClickListener {
            var correo = binding.edtLoginCorreo.text.toString()
            var contrasenia = binding.edtLoginCorreo.text.toString()
            val user = UserResponse(correo, contrasenia)
            postSignIn(user)
        }
    }

    private fun postSignIn(user: UserResponse) {

        // esto son codigos sencillos para provar la navegacion del login

        val tvGoRegistrarUsuario = findViewById<TextView>(R.id.tv_registrar_usuario)
        tvGoRegistrarUsuario.setOnClickListener{
            goRegistarUsuario()
        }

        val btnGoHomeUsuario = findViewById<TextView>(R.id.btn_login_iniciar_sesion)
        btnGoHomeUsuario.setOnClickListener{
            goHomeUsuario()
        }

        //termina aqui

        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIService::class.java).signIn(user)
            val data_user = call.body()
            runOnUiThread {
                if (data_user?.status == "success") {
                    SharedApp.prefs.id = data_user.id_user.toString()
                    SharedApp.prefs.name = data_user.name.toString()
                    Log.d("API funciona", "" + data_user.id_user)
                    showMainMenu()
                } else if (data_user?.status == "invalid") {
                    showErrorDialog(data_user.message.toString())
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

    private fun showMainMenu() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(getString(R.string.url_base))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun goRegistarUsuario(){
        val i= Intent(this,RegistroUsuario::class.java)
        startActivity(i)
    }

    private fun goHomeUsuario(){
        val i= Intent(this,HomeUsuario::class.java)
        startActivity(i)
    }
}
