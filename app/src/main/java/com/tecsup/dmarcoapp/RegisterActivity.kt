package com.tecsup.dmarcoapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tecsup.dmarcoapp.R
import com.tecsup.tecsupapp.RegistroViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {
    private lateinit var viewModel: RegistroViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        title = "Registro de usuarios"
        viewModel = ViewModelProvider(this).get(RegistroViewModel::class.java)

        btnRegistrar.setOnClickListener{
            val nombre = edtNombre.text.toString()
            val apellidos = edtApellido.text.toString()
            val correo = edtCorreo.text.toString()
            val clave = edtClave.text.toString()
            viewModel.registrar(correo,clave,nombre,apellidos)
        }

        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.userRegisterFirebase.observe(this) {
            if (it) {
                Toast.makeText(this, "Registro Correcto",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registro Incorrecto",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

}