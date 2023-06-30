package com.tecsup.dmarcoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tecsup.tecsupapp.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val actionBar = supportActionBar
        actionBar?.hide()

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnIniciarSesion.setOnClickListener{
            val correo = edtCorreo.text.toString()
            val clave = edtClave.text.toString()
            viewModel.login(correo,clave)
        }


        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        observableViewModel()

    }

    fun observableViewModel(){
        viewModel.userLoginFirebase.observe(this){
            if (it){
                //login correcto
                startActivity(Intent(this, PlatosActivity::class.java))
            }else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }//login incorrecto
        }
    }
}