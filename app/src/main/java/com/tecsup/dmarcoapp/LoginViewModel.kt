package com.tecsup.tecsupapp

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    val userLoginFirebase = MutableLiveData<Boolean>()

    fun login(correo: String, clave: String){
        if(correo.isEmpty() or clave.isEmpty()){
            //Mostrar mensaje de error
            userLoginFirebase.value = false

        }else{
            //login con firebase
            loginFirebase(correo, clave)
        }
    }

    private fun loginFirebase(correo: String, clave: String){
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(correo, clave)
            .addOnCompleteListener(Activity()){ task ->
                if(task.isSuccessful){
                    val uid = task.result?.user?.uid
                    if (uid !=null){
                        userLoginFirebase.value = true
                    }
                } else {
                    userLoginFirebase.value = false
                }
            }
    }
}