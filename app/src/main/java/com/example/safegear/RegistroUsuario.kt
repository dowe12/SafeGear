package com.example.safegear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.safegear.databinding.ActivityRegistroUsuarioBinding
import com.example.safegear.model.SharedApp
import com.example.safegear.model.UserBodyRegister
import com.example.safegear.network.APIService
import com.example.safegear.ui.mechanic.HomeMechanicActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroUsuario : AppCompatActivity() {
    lateinit var binding:ActivityRegistroUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarUsuario.setOnClickListener{
            goHomeUsuario()
        }

        binding.btnCancelarUsuario.setOnClickListener{
            goUsuarioLogin()
        }

    }

    private fun goHomeUsuario(){
        val name                = binding.etNombreRegister.text.toString()
        val email               = binding.etCorreoRegister.text.toString()
        val password            = binding.etContraseniaRegister.text.toString()
        val passwordConfirmated = binding.etConfContraseniaRegister.text.toString()
        val roll = "Usuario"

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmated.isEmpty()){
            return showDialog("Por favor ingrese todos los campos!")
        }
        if (password.trim() == passwordConfirmated.trim()){
            CoroutineScope(Dispatchers.IO).launch {
                val user = UserBodyRegister(name, email, password, roll)
                val call =
                    getRetrofit().create(APIService::class.java).register(user)
                val dataUser = call.body()
                runOnUiThread {
                    when (dataUser?.status) {
                        "success" -> {
                            val rol = dataUser.rol_id.toString()
                            SharedApp.prefs.id              = dataUser.id_user.toString()
                            SharedApp.prefs.rolId           = rol
                            SharedApp.prefs.name            = dataUser.nombre.toString()
                            SharedApp.prefs.lastname        = dataUser.apellido.toString()
                            SharedApp.prefs.identification  = dataUser.identificacion.toString()
                            SharedApp.prefs.jwt             = dataUser.jwt.toString()
                            Log.d("API funciona", "" + dataUser.id_user)
                            showMainMenu(rol)
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
        } else if (password.trim() != passwordConfirmated.trim())
        {
            showErrorDialog("Las contraseñas no coinciden!")
        }

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

    private fun goUsuarioLogin(){
        val i= Intent(this,Login::class.java)
        startActivity(i)
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