package com.example.safegear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RegistroUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        val btnGoHomeUsuario = findViewById<TextView>(R.id.btn_registrar_usuario)
        btnGoHomeUsuario.setOnClickListener{
            goHomeUsuario()
        }

        val btnGologin = findViewById<TextView>(R.id.btn_cancelar_usuario)
        btnGologin.setOnClickListener{
            goUsuarioLogin()
        }

    }

    private fun goHomeUsuario(){
        val i= Intent(this,HomeUsuario::class.java)
        startActivity(i)
    }

    private fun goUsuarioLogin(){
        val i= Intent(this,Login::class.java)
        startActivity(i)
    }
}