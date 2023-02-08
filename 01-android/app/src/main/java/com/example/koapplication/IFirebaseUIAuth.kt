package com.example.koapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

class IFirebaseUIAuth : AppCompatActivity() {
    // Callback del intent Login
    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res: FirebaseAuthUIAuthenticationResult ->
            if (res.resultCode === RESULT_OK) {
                if (res.idpResponse != null) {
                    seLogeo(res.idpResponse!!)
                }
            }
        }

    fun seLogeo(res: IdpResponse) {
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogin.visibility = View.INVISIBLE
        btnLogin.visibility = View.VISIBLE
        if(res.isNewUser == true){
            registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse) {
        //FIREBASE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirebase_uiauth)

        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
            // Create and launch sign on intent
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            // Respuestas del intent de login
            signInLauncher.launch(signInIntent)
        }

    }
}