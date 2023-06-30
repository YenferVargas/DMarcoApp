package com.tecsup.tecsupapp

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.concurrent.timerTask

class RegistroViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    val userRegisterFirebase = MutableLiveData<Boolean>()

    fun registrar(correo: String, clave: String, nombre: String,
                  apellido: String) {
        if (correo.isEmpty() or clave.isEmpty() or nombre.isEmpty() or
            apellido.isEmpty()) {
            userRegisterFirebase.value = false
        } else {
            registrarFirebase(correo,clave, nombre, apellido)
        }
    }

    private fun registrarFirebase(correo: String, clave: String, nombre: String,
                                  apellido: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(correo,clave)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    val uid = task.result?.user?.uid
                    if (uid != null ){
                        registrarFirestore(uid, nombre, apellido, correo)
                    }
                } else {
                    userRegisterFirebase.value = false
                }
            }
    }

    private fun registrarFirestore(uid: String, nombre: String, apellido: String,
                                   correo: String) {
        val usuario = UsuarioFirestore(apellido,correo,nombre)
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("usuarios").document(uid).set(usuario)
            .addOnCompleteListener(Activity()) { task ->
                userRegisterFirebase.value = task.isSuccessful
            }
    }
}
data class UsuarioFirestore(
    var apellido: String,
    var correo: String,
    var nombre: String
)