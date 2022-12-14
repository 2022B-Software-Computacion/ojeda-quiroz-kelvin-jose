package com.example.koapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.koapplication.databinding.ActivityAcicloVidaBinding

class ACicloVida : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAcicloVidaBinding
    var textoGlobal = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAcicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_aciclo_vida)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        mostrarSnackBar("onCreate-")
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackBar("onStart-")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackBar("onRestart-")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackBar("onResume-")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackBar("onPause-")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackBar("onStop-")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackBar("onDestroy-")
    }

    fun mostrarSnackBar(texto: String) {
        textoGlobal += texto
        Snackbar.make(findViewById(R.id.cl_ciclo_vida), textoGlobal, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_aciclo_vida)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            //Solamente podemos guardar variables primitivas
            putString("textoGuardado", textoGlobal)
            //putString("numeroGuardado", numero)

        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textoRecuperado: String? = savedInstanceState.getString("textoGuardado")
        // val textoRecuperado:Int? = savedInstanceState.getInt("numeroGuardado")
        if (textoRecuperado != null) {
            mostrarSnackBar(textoRecuperado)
            textoGlobal = textoRecuperado
        }
    }
}