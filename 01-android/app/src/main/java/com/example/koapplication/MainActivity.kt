package com.example.koapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val contenidoIntentExplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            if (result.data != null) {
                val data = result.data
                Log.i("Intente-epn", "${data?.getStringExtra("nombreModificado")}")
            }
        }
    }
    val contenidoIntentImplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            if (result.data != null) {

                val uri: Uri = result.data!!.data!!
                val cursor = contentResolver.query(
                    uri, null, null, null, null, null
                )
                cursor?.moveToFirst()
                val indiceTelefono = cursor?.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )
                val telefono = cursor?.getString(indiceTelefono!!)
                cursor?.close()
                Log.i("intent-epn", "Telefono $telefono")

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EBaseDatos.tablaEntrenador = ESqliteHelperEntrenador(this)
        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite.setOnClickListener { irActividad(ECrudEntrenador::class.java) }


        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida.setOnClickListener { irActividad(ACicloVida::class.java) }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener { irActividad(BListView::class.java) }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            contenidoIntentImplicito.launch(intentConRespuesta)
        }

        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

        val botonRecyclerView = findViewById<Button>(R.id.btn_recycler_view)
        botonRecyclerView.setOnClickListener {
            irActividad(GRecyclerView::class.java)
        }

        val botonMaps   = findViewById<Button>(R.id.btn_google_maps)
        botonMaps.setOnClickListener {
            irActividad(HGoogleMapsActivity::class.java)
        }

        val botonFirebaseUI   = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonMaps.setOnClickListener {
            irActividad(IFirebaseUIAuth::class.java)
        }

        val botonFirestore = findViewById<Button>(R.id.btn_intent_firestore)
        botonFirestore.setOnClickListener {
            irActividad(JFirebaseFirestone::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        //Envair parametros solamente variables primitivas
        intentExplicito.putExtra("Nombre", "Kelvin")
        intentExplicito.putExtra("Apellido", "Ojeda")
        intentExplicito.putExtra("Edad", 19)
        intentExplicito.putExtra("entrenadorPrincipal", BEntrenador(1,"KELVIN", "PARCELABLE"))
        contenidoIntentExplicito.launch(intentExplicito)
    }
}